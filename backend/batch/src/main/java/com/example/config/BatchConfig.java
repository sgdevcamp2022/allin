package com.example.config;

import com.example.domain.Message;
import com.example.domain.User;
import com.example.repository.ChatRepository;
import com.example.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
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
@RequiredArgsConstructor
public class BatchConfig {

  private final UserRepository userRepository;
  private final ChatRepository chatRepository;

  @Bean
  public Tasklet myTasklet() {
    return new MyTasklet(chatRepository, userRepository);
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

  @RequiredArgsConstructor
  private class MyTasklet implements Tasklet {

    private final ChatRepository chatRepository;
    private final UserRepository userRepository;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
      List<User> blockedUsers = userRepository.findAllByIsDone(false);
      blockedUsers.forEach(blockedUser -> {
        List<Message> messages = chatRepository.findAllByTopicIdAndSender(
          blockedUser.getTopicId(), blockedUser.getName());
        messages.forEach(message -> {
          message.hide();
          chatRepository.save(message);
        });
        blockedUser.done();
        userRepository.save(blockedUser);
      });

      return RepeatStatus.FINISHED;
    }
  }
}
