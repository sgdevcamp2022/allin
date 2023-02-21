# 배치서버
## 역할
* 일정 시간마다 채팅 차단된 사용자의 전체 메시지 가림 처리

## 기술스택
* Java 17
* Springboot 3.0.2
* Spring Batch
* Spring Scheduler
* Spring Data JPA
* Redis
* Mongo DB
* Mysql8

## 아키텍처
![스크린샷 2023-02-18 오전 1 36 58](https://user-images.githubusercontent.com/60775067/219711696-96dd9789-d0ca-4541-99b2-a13910572932.png)


## 제공 기능
|기능|설명|
|------|---|
|매시지 가림 처리|채팅 차단된 사용자의 전체 메시지 가림 처리|

## 구현
```java
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
```
```java
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
```
## 프로젝트 관련 기록
[@ControllerAdvice 클래스에서 body에 데이터 넣어서 반환할 때 view를 찾는 문제](https://velog.io/@gkdud583/RestController-void-%EB%B0%98%ED%99%98%EC%8B%9C-view%EB%A5%BC-%EC%B0%BE%EB%8A%94-%EB%AC%B8%EC%A0%9C) 

[spring data redis 데이터 조회 시 이상하게 나오는 문제](https://velog.io/@gkdud583/redis-%EB%8D%B0%EC%9D%B4%ED%84%B0-%EC%A1%B0%ED%9A%8C%EC%8B%9C-%EC%9D%B4%EC%83%81%ED%95%98%EA%B2%8C-%EB%82%98%EC%98%A4%EB%8A%94-%EB%AC%B8%EC%A0%9C)

[brew services start redis로 실행 시 주의할 점](https://velog.io/@gkdud583/brew-services-start-redis%EB%A1%9C-%EC%8B%A4%ED%96%89-%EC%8B%9C-%EC%A3%BC%EC%9D%98%ED%95%A0-%EC%A0%90)

[채팅 아키텍처 설계하기 v1](https://velog.io/@gkdud583/%EC%B1%84%ED%8C%85-%EC%95%84%ED%82%A4%ED%85%8D%EC%B2%98-%EC%84%A4%EA%B3%84%ED%95%98%EA%B8%B0)

[채팅 아키텍처 설계하기 v2](https://velog.io/@gkdud583/%EC%B1%84%ED%8C%85-%EC%95%84%ED%82%A4%ED%85%8D%EC%B2%98-%EC%84%A4%EA%B3%84%ED%95%98%EA%B8%B0-v2)

[StompSession send() ClassCastException](https://velog.io/@gkdud583/StompSession-send-ClassCastException)

[StompSession BlockingQueue 메시지 전달 안되는 문제](https://velog.io/@gkdud583/StompSession-%EB%A9%94%EC%8B%9C%EC%A7%80-%EC%A0%84%EB%8B%AC-%EC%95%88%EB%90%98%EB%8A%94-%EB%AC%B8%EC%A0%9C)

[Redis cluster](https://velog.io/@gkdud583/Redis-cluster)

[NHN 분산 시스템에서 데이터를 전달하는 효율적인 방법](https://velog.io/@gkdud583/NHN-%EB%B6%84%EC%82%B0-%EC%8B%9C%EC%8A%A4%ED%85%9C%EC%97%90%EC%84%9C-%EB%8D%B0%EC%9D%B4%ED%84%B0%EB%A5%BC-%EC%A0%84%EB%8B%AC%ED%95%98%EB%8A%94-%ED%9A%A8%EC%9C%A8%EC%A0%81%EC%9D%B8-%EB%B0%A9%EB%B2%95)

[embedded mongoDB Set the de.flapdoodle.mongodb.embedded.version property 오류](https://velog.io/@gkdud583/embedded-mongoDB-Set-the-de.flapdoodle.mongodb.embedded.version-property-%EC%98%A4%EB%A5%98)

[testcontainer로 redis 테스트하기](https://velog.io/@gkdud583/testcontainer%EB%A1%9C-redis-%ED%85%8C%EC%8A%A4%ED%8A%B8%ED%95%98%EA%B8%B0)
[testcontainer BeforeAllCallback 여러번 호출되는 문제](https://velog.io/@gkdud583/testcontainer-BeforeAllCallback-%EC%97%AC%EB%9F%AC%EB%B2%88-%ED%98%B8%EC%B6%9C%EB%90%98%EB%8A%94-%EB%AC%B8%EC%A0%9C)

[웹소켓 서버 수평 확장](https://velog.io/@gkdud583/%EC%9B%B9%EC%86%8C%EC%BC%93-%EC%84%9C%EB%B2%84-%EC%88%98%ED%8F%89-%ED%99%95%EC%9E%A5-ntpuhlar)

