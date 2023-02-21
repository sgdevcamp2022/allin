package com.example.repository;

import com.example.domain.Report;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Long> {
  Optional<Report> findByTopicIdAndReportedUser(String topicId, String reportedUser);
}
