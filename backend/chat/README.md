# 채팅서버

## 역할
* 웹소켓을 이용해 서버와 클라이언트 간 통신
* kafka를 통해 신고 서버와 통신하며 10번 이상 신고된 사용자 정보를 redis cache에 업데이트

## 기술스택
* Java 17
* Springboot 3.0.1
* WebSocket, STOMP, SockJS
* kafka
* Redis
* Mongo DB


## 아키텍처
![스크린샷 2023-02-17 오후 2 52 24](https://user-images.githubusercontent.com/60775067/219560621-ac317d43-0f0f-433c-971f-b794af17b876.png)




## 제공 기능
|기능|설명|
|------|---|
|메시지 전송|기본적인 채팅 기능|
|redis pub sub을 이용한 다중 채팅 서버|메시지 브로커를 이용한 채팅 서버 동기화 작업|
|kafka를 이용한 신고 서버와 통신 및 사용자 메시지 전송 차단|메시지큐를 이용하여 채팅 차단 사용자에 대한 정보를 받고 레디스 캐시에 업데이트하여 해당 사용자의 메시지 전송 요청이 왔을 때 채팅 차단|

## 구현
### 1. 채팅 서버 동기화
```java
  @Override
  public void send(String id, ChatMessageRequest message) {
    Topic foundTopic = topicService.findById(id);
    if (foundTopic.isClose()) {
      throw new IllegalStateException(CLOSED_TOPIC.getMessage());
    }
    if (userService.isBlockedUser(foundTopic.getId(), message.getSender())) {
      throw new IllegalStateException(BLOCKED_USER.getMessage());
    }
    Message sendMessage = Message.of(foundTopic.getId(), message.getSender(), message.getContent(),
      foundTopic.getExpireAt());
    chatRepository.save(sendMessage);
    publisher.publish(foundTopic.getId(), message);
  }
```

```java
public class RedisPublisher implements MessagePublisher {

  private final RedisTemplate<String, Object> redisTemplate;

  @Override
  public void publish(String topic, ChatMessageRequest message) {
    redisTemplate.convertAndSend(topic, message);
  }
}
```
```java
public class RedisSubscriber implements MessageListener {

  private final RedisTemplate<String, Object> redisTemplate;
  private final ObjectMapper objectMapper;

  private final SimpMessageSendingOperations simpMessageSendingOperations;

  @Override
  public void onMessage(Message message, byte[] pattern) {
    String id = redisTemplate.getStringSerializer().deserialize(message.getChannel());
    String messageBody = redisTemplate.getStringSerializer()
                                      .deserialize(message.getBody());
    ChatMessageRequest chatMessage = null;
    try {
      chatMessage = objectMapper.readValue(messageBody, ChatMessageRequest.class);
    } catch (JsonProcessingException e) {
      log.info(e.getMessage());
      return;
    }
    ChatMessageResponse sendChatMessage = ChatMessageResponse.of(chatMessage.getSender(),
      chatMessage.getContent());
    simpMessageSendingOperations.convertAndSend("/topic/" + id, sendChatMessage);
  }
}
```

### 2. kafka를 이용한 사용자 채팅 차단
```java
public class ReportConsumer {

  private final UserService userService;

  @KafkaListener(
    topics = "${spring.kafka.topicName}",
    groupId = "${spring.kafka.consumer.group-id}"
  )

  public void consume(ReportEvent reportEvent) {
    userService.block(reportEvent.getTopicId(), reportEvent.getReportedUser());
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
