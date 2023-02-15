## Media Server

### 간단 소개

![Group 1857](https://user-images.githubusercontent.com/102807742/218668956-b750afd3-88f6-4990-8144-0540cc3adddd.png)


위의 사진은 LINE LIVE 인코더 레이어 구조를 나타낸 사진입니다.

LINE Engineering 블로그의 [LINE LIVE 서비스의 인코더 레이어 구조](https://engineering.linecorp.com/ko/blog/the-structure-of-the-line-live-s-encoder-layer/#cdn-origin-server)에서는 미디어 서버에서는 다음과 같은 기능을 제공한다고 합니다.

+ 사용자가 송출하는 **RTMP 신호를 시청자가 볼 수 있게 변환**하는 작업
+ **RTMP 신호 인증**
+ **방송 녹화**

해당 프로젝트에는 RTMP 신호를 OBS로부터 받고 이를 시청자가 볼 수 있도록 HLS로 변환하는 작업을 합니다.(NGINX RTMP)

그리고 변환된 파일을 카프카를 통해 CDN 서버로 전달합니다.(Kafka)

### 아키텍처

![2-6아키텍쳐_2_1](https://user-images.githubusercontent.com/102807742/218659328-91450a9c-05db-443d-98f2-3f1278b5b7c2.jpg)

OBS에서 RTMP 신호를 미디어 서버의 NGiNX RTMP 모듈을 통해 `.m3u8`, `.ts` 로 변환합니다.

변환한 파일을 미디어 서버에서 `live-topic`, `liveIndex-topic`으로 Kafka 브로커로 메시지를 전송 합니다.

전송된 파일은 `live-topic`, `liveIndex-topic`을 구독하고 있는 CDN 서버에서 메시지를 전송 받습니다.

### 기술 스택

+ Java 11
+ Springboot
+ Kafka
+ Nginx-Rtmp

### QUESTION

https://github.com/belljun3395/allin/blob/belljun/backend/media/docs/question/QUESTION.md