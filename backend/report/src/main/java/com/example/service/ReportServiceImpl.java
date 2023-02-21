package com.example.service;

import com.example.domain.Report;
import com.example.dto.ReportRequest;
import com.example.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

  private static final long THRESHOLD = 10;
  private final ReportRepository reportRepository;

  private final ReportProducer reportProducer;

  @Override
  // dirty checking
  public synchronized void report(ReportRequest request) {
    transactionalReport(request);
  }

  // @Transactional과 synchrozied를 함께 적용하기 위해 사용하는 메서드입니다.
  @Transactional
  protected void transactionalReport(ReportRequest request) {
    reportRepository.findByTopicIdAndReportedUser(request.getTopicId(), request.getReportedUser())
                    .ifPresentOrElse(
                      report -> {
                        report.increaseCount();
                        sendMessage(report.getCount(), request.getTopicId(),
                          request.getReportedUser());
                      },
                      () -> reportRepository.save(
                        Report.of(request.getTopicId(), request.getReportedUser(),
                          request.getReporter(), request.getMessage(), request.getReason())));
  }


  private void sendMessage(long count, String topicId, String reportedUser) {
    if (count == THRESHOLD) {
      reportProducer.send(topicId, reportedUser);
    }
  }
}
