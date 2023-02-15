package com.example.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import com.example.common.ReportReason;
import com.example.domain.Report;
import com.example.dto.ReportRequest;
import com.example.repository.ReportCountRepository;
import com.example.repository.ReportRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ReportServiceImplTest {

  @Mock
  ReportRepository reportRepository;

  @Mock
  ReportCountRepository reportCountRepository;

  @InjectMocks
  ReportServiceImpl reportService;

  @Nested
  @DisplayName("report 메서드는")
  class DescribeReport {

    @Nested
    @DisplayName("유효한 값이 전달되면")
    class ContextWithValidData {

      @Test
      @DisplayName("reportRepository.save()와 reportCountRepository.increaseReportCount()를 호출한다")
      void ItCallsSaveAndIncreaseReportCount() {
        // given
        ReportRequest reportRequest = new ReportRequest("topic1", "user2",
          "user1", "제 주민번호는 990115-212345예요", ReportReason.PERSONAL_INFORMATION_DISCLOSURE);

        given(reportRepository.save(any(Report.class)))
          .willReturn(Report.of(reportRequest.getReportedUser(), reportRequest.getReporter(), reportRequest.getMessage(),
            reportRequest.getReason()));

        // when
        reportService.report(reportRequest);

        // then
        verify(reportRepository).save(any(Report.class));
        verify(reportCountRepository).increaseReportCount(anyString(), anyString());
      }
    }
  }
}
