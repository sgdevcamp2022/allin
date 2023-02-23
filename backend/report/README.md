# 신고서버
## 역할
* 사용자 신고 및 10번 이상 신고된 사용자의 경우 kafka 메시지 발행

## 기술스택
* Java 17
* Springboot 3.0.2
* Spring Data JPA
* Kafka
* MySQL8

## 아키텍처
![스크린샷 2023-02-21 오전 11 07 11](https://user-images.githubusercontent.com/60775067/220229411-bb87be45-ac1a-45f2-9272-ee31a8df593c.png)



## 제공 기능
|기능|설명|
|------|---|
|신고하기|사용자 채팅 신고 기능|
|kafka를 이용하여 채팅 서버와 통신|10번 이상 신고된 사용자의 경우 kafka로 메시지 발행, 채팅서버가 메시지를 가져가 처리|



## 구현
```java
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

```
```java
private void sendMessage(long count, String topicId, String reportedUser) {
    if (count == THRESHOLD) {
      reportProducer.send(topicId, reportedUser);
    }
  }
```
```java
  public void send(String topicId, String reportedUser) {
    Message<ReportEvent> message = MessageBuilder
      .withPayload(new ReportEvent(topicId, reportedUser))
      .setHeader(KafkaHeaders.TOPIC, topic.name())
      .build();
    kafkaTemplate.send(message);
  }
```


## 이슈
### 동시성 문제

로직 v1

count 정보를 저장하기 위해 redis를 사용하고 신고 정보를 저장하기 위해 mysql을 사용하는 구조

기존에는 스케줄러에서 10번 이상 신고된 사용자에 대해 kafka 메시지 발행 로직을 처리하려고 했었어서 읽기 성능을 위해 redis를 사용하였음.

그런데 이후 신고하기 기능에서 해당 로직을 처리하게 되면서 count 정보만 따로 관리할 필요성이 없어짐.
```java
  @Transactional
  public void report(ReportRequest request) {
    long count = reportCountRepository.increaseReportCount(request.getTopicId(),
      request.getReportedUser());
    sendMessage(count, request.getTopicId(), request.getReportedUser());
    Report report = Report.of(request.getReportedUser(), request.getReporter(), request.getMessage(), request.getReason());
    reportRepository.save(report);
```
로직 v2

그래서 report 테이블에 count 컬럼을 추가해서 mysql 하나만 사용하는 방식으로 수정

레코드가 존재하지 않을 경우 insert, 존재할 경우 count update를 수행하는 데 여러 스레드가 메서드에 동시에

접근할 때 레코드가 여러 개 저장되는 문제 발생, 그래서 이후 findByTopicIdAndReportedUser()에서 두 개 이상의 레코드가 반환되어 exception 발생

```java
@Transactional
public void report(ReportRequest request) {
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
```

jmeter로 테스트 한 결과
![스크린샷 2023-02-20 오전 12 54 36](https://user-images.githubusercontent.com/60775067/220229266-d8a33735-b9a8-4c3f-af44-530ded11defc.png)
![스크린샷 2023-02-20 오전 12 54 13](https://user-images.githubusercontent.com/60775067/220229295-03ca5734-37dd-4d19-b239-e773e25edf77.png)

로직 v3

레코드를 찾고 insert 또는 update 하는 로직을 처리할 때 다른 스레드가 끼어들지 못하도록 하기 위해 synchronized 사용
그런데 여전히 동일한 문제 발생
```java
@Transactional
public synchronized void report(ReportRequest request) {
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
```

로직 v4

@transactional과 synchronized를 함께 사용할 때 발생하는 문제임을 알고 메서드 분리
```java
@Override
  public synchronized void report(ReportRequest request) {
    transactionalReport(request);
  }

  // @Transactional과 synchrozied를 함께 적용하기 위해 사용하는 메서드입니다.
  @Transactional   // dirty checking
  protected void transactionalReport(ReportRequest request) { // proxy로 감싸서 실행되기 때문에 private 사용 불가
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

```
테스트 성공
![스크린샷 2023-02-20 오전 1 30 26](https://user-images.githubusercontent.com/60775067/220230213-5ae8f2f0-fa01-4541-8a18-780903062b37.png)


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


