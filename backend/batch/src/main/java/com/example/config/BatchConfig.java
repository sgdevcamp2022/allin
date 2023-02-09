package com.example.config;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class BatchConfig {

  @Bean
  public Tasklet myTasklet() {
    return new MyTasklet();
  }

  @Bean
  public Step myStep(JobRepository jobRepository, Tasklet tasklet,
    PlatformTransactionManager platformTransactionManager) {
    return new StepBuilder("myStep", jobRepository)
      .tasklet(tasklet, platformTransactionManager)
      .build();
  }

  @Bean
  public Job myJob(JobRepository jobRepository, Step step) {
    return new JobBuilder("myJob", jobRepository)
      .start(step)
      .build();
  }

  private class MyTasklet implements org.springframework.batch.core.step.tasklet.Tasklet {

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext)
      throws Exception {
      return RepeatStatus.FINISHED;
    }
  }
}
