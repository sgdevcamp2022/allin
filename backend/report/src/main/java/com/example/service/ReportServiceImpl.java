package com.example.service;

import com.example.domain.Report;
import com.example.dto.ReportRequest;
import com.example.repository.ReportCountRepository;
import com.example.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {
  private static final long THRESHOLD = 10;
  private final ReportRepository reportRepository;
  private final ReportCountRepository reportCountRepository;

  private final ReportProducer reportProducer;

  @Override
  @Transactional
  public void report(ReportRequest request) {
    long count = reportCountRepository.increaseReportCount(request.getTopicId(),
      request.getReportedUser());
    sendMessage(count, request.getTopicId(), request.getReportedUser());
    Report report = Report.of(request.getReportedUser(), request.getReporter(), request.getMessage(), request.getReason());
    reportRepository.save(report);
  }

  private void sendMessage(long count, String topicId, String reportedUser) {
    if (count == THRESHOLD) {
      reportProducer.send(topicId, reportedUser);
    }
  }
}
