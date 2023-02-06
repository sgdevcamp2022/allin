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

  private final ReportRepository reportRepository;
  private final ReportCountRepository reportCountRepository;

  @Override
  @Transactional
  public void report(ReportRequest request) {
    reportCountRepository.increaseReportCount(request.getTopicId(), request.getReportedUser());
    Report report = Report.of(request.getReportedUser(), request.getMessage(), request.getReason());
    reportRepository.save(report);
  }
}
