package com.example.config;

import java.util.HashMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class SchedulerConfig {

  private final JobLauncher jobLauncher;
  private final Job job;

  @Scheduled(cron = "0 */5 * * * *")
  public void runJob() {
    HashMap<String, JobParameter<?>> confMap = new HashMap<>();
    confMap.put("time", new JobParameter(System.currentTimeMillis(), long.class));
    JobParameters jobParameters = new JobParameters(confMap);

    try {
      jobLauncher.run(job, jobParameters);
    } catch (JobExecutionAlreadyRunningException | JobInstanceAlreadyCompleteException |
             JobParametersInvalidException | JobRestartException e) {
      log.error(e.getMessage());
    }
  }
}
