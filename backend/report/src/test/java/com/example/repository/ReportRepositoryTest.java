package com.example.repository;

import static com.example.common.ReportReason.UNPLEASANT_EXPRESSION;
import static org.assertj.core.api.Assertions.assertThat;

import com.example.MySQLTestContainerConfig;
import com.example.domain.Report;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@ExtendWith(MySQLTestContainerConfig.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class ReportRepositoryTest {

  @TestConfiguration
  @EnableJpaAuditing
  static class config {
  }
  @Autowired
  ReportRepository reportRepository;

  @AfterEach
  void clear() {
    reportRepository.deleteAll();
  }

  @Nested
  @DisplayName("findByTopicIdaAndReportedUser 메서드는")
  class DescribeFindByTopicIdaAndReportedUser {

    @Nested
    @DisplayName("topicId와 reportedUser를 가진 report가 있다면")
    class ContextWithReportExists {

      @Test
      @DisplayName("report를 반환한다")
      void ItReturnsReport() {
        // given
        Report report = Report.of("topic1", "신고된 유저", "신고한 유저", "별로에요!!", UNPLEASANT_EXPRESSION);
        reportRepository.save(report);

        // when
        Optional<Report> result = reportRepository.findByTopicIdAndReportedUser(
          report.getTopicId(), report.getReportedUser());

        // then
        assertThat(result).isPresent();
      }
    }

    @Nested
    @DisplayName("topicId와 reportedUser를 가진 report가 없다면")
    class ContextWithNonexistentReport {

      @Test
      @DisplayName("Optional.empty를 반환한다")
      void ItReturnsEmpty() {
        // given
        // when
        Optional<Report> result = reportRepository.findByTopicIdAndReportedUser("topic1", "신고당한 유저");

        // then
        assertThat(result).isNotPresent();
      }
    }
  }
}
