# websocket handshake

- method: websocket
- url: /ws

# 채널 구독

- method: websocket
- url: /topic/{topicId}

# 메시지 전송

- method: websocket
- url: /chat/{topicId}/send

## 요청

```json
/chat/{topicId}/send

{
 "sender": "user1", // 유저의 닉네임
 "content": "안녕하세요! 반갑습니다." // 보내고자 하는 메시지 
}
```

# 메시지 이력 조회

- method: GET
- url: /api/v1/chats/{topicId}?page={pageIndex}

## 요청

```jsx
/api/v1/chats/{topicId}?page=페이지번호
```

topicId: 채팅방 아이디

page: 페이지 번호, 0부터 시작

19개씩, 최신 작성순으로 순차적으로 반환함

ex)

처음 요청은 0

그다음 요청은 1

그다음 요청은 2

마지막은 응답을 어찌하나요??

-채승-

**→ 마지막 페이지는 결과가 0으로 오는걸로 처리해주면 감사할 것 같습니다!**

## 응답 - 성공

```json
{
  "result": "success",
   "data": [
       {
           "sender": "마틴파울러", // 유저 닉네임
      "content": "hello!!" // 메시지 내용
    },
    {
          "sender": "최범균",
           "content": "도메인 주도 개발 시작하기 읽어보세요.."
    } 
  ] 
}
```

## 응답 - 실패

존재하지 않는 채팅방일 경우 응답

```json
{
    "timestamp": "2023-02-15T05:30:32.198+00:00",
    "code": "4005002",
    "error": "java.lang.IllegalArgumentException",
    "message": "존재하지 않는 topic입니다."
}
```

# 채널 생성 (방송 서버 호출용)

- method: POST
- url: /api/v1/topics

## 요청

```jsx
/api/v1/topics

{
    "topicId": "topic1", //채팅방 id
  "expireAt": "2019-11-12T16:34:30.388" //방송 종료 시간
}
```

## 응답

- 비동기 처리를 위해 응답을 받지 않음

# 신고하기

- method: POST
- url: /api/v1/reports

## 요청

```jsx
/api/v1/reports

{
    "topicId": "topic1", //채팅방 id
  "reportedUser": "정하영", //신고할 사용자 닉네임
  "reporter": "윤성우", //신고자 닉네임
  "message": "방송 너무 별로에요", //신고할 메시지
  "reason": "SPAM_OR_DUPLICATE_WRITING" //신고 사유

}
```

신고사유는 아래 형식을 따릅니다.

```jsx
SPAM_OR_DUPLICATE_WRITING("스팸홍보/도배글입니다."),
PORNOGRAPHY("음란물입니다."),
ILLEGAL_INFORMATION("불법정보를 포함하고 있습니다."),
HARM_TO_ADOLESCENTS("청소년에게 유해한 내용입니다."),
SWEAR_WORD_OR_LIFE_THREAT_OR_HATE_EXPRESSION_OR_DISCRIMINATORY_EXPRESSION(
  "욕설/생명경시/혐오/차별적 표현입니다."),
PERSONAL_INFORMATION_DISCLOSURE("개인정 노출 게시물입니다"),
UNPLEASANT_EXPRESSION("불쾌한 표현이 있습니다.");
```

## 응답 - 성공

```json
{
  "result": "success",
  "data": null
}
```

## 응답 - 실패

topicId가 없거나 닉네임이 없거나 혹은 8자를 넘은 경우 그리고 메시지가 없거나 100자를 넘은 경우 신고 사유가 없는 경우 다음의 응답이 오게 됩니다.

```json
{
    "timestamp": "2023-02-15T05:24:48.360+00:00",
    "code": "4006001",
    "error": "org.springframework.web.bind.MethodArgumentNotValidException",
    "message": "요청 데이터가 올바르지 않습니다."
}
```
