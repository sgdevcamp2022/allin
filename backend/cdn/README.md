## CDN Server

### 간단 소개

![Group 1859](https://user-images.githubusercontent.com/102807742/218960155-ca1f8a5c-1c99-4267-a455-aa755b4d7a73.png)

CDN 서버는 시청자가 영상을 요청하는 서버입니다.

해당 프로젝트의 CDN 서버는 Origin 서버 역활을 하는 Kafka 브로커에서 `live-topic` 그리고 `liveIndex-topic`을 구독하여 

**영상에 대한 메시지를 받아** 시청자에게 제공할 영상을 캐싱합니다.

프로젝트에 대한 자세한 사항은 [링크](https://github.com/belljun3395/allin/tree/belljun/backend/cdn/docs)를 통해 소개하도록 하겠습니다.

### 아키텍처

![Group 1863](https://user-images.githubusercontent.com/102807742/218960456-420397b8-391f-4f9d-8313-70d70db73c51.png)

CDN 서버에서 Kafka 브로커에서 받은 메시지를 통해 영상을 생성합니다.

NGNIX RTMP는 생성한 영상을 HLS 형식으로 시청자(Client)의 요청에 따라 제공합니다.

### 기술 스택

+ Java 11
+ Springboot
+ Kafka
+ Nginx-Rtmp

### QUESTION

Media Server와 동일하여 생략하였습니다.