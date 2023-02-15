## MEDIA DOCS

### INDEX

+ [kafka](https://github.com/belljun3395/allin/tree/belljun/backend/media/docs/kafka)
  + [ACKS_CONFIG](https://github.com/belljun3395/allin/blob/belljun/backend/media/docs/kafka/ACKS_CONFIG.md)
  + [LiveDTO](https://github.com/belljun3395/allin/blob/belljun/backend/media/docs/kafka/LiveDTO.md)
+ [question](https://github.com/belljun3395/allin/tree/belljun/backend/media/docs/question)
  + [QUESTION](https://github.com/belljun3395/allin/blob/belljun/backend/media/docs/question/QUESTION.md)
+ [INTRODUCE](https://github.com/belljun3395/allin/blob/belljun/backend/media/docs/INTRODUCE.md)

## INTRODUCE

간단 소개에서 설명하였듯 미디어 서버는 아래와 같은 기능을 수행합니다.

+ 사용자가 송출하는 **RTMP 신호를 시청자가 볼 수 있게 변환**하는 작업
+ **RTMP 신호 인증**
+ **방송 녹화**
+ CDN 서버로의 **영상 전송**

### NGINX RTMP

#### RTMP 신호 변환

<img width="858" alt="스크린샷 2023-02-14 오후 4 05 50" src="https://user-images.githubusercontent.com/102807742/218663785-36811a41-96a2-4918-8978-aba78f5ec1b6.png">

위 사진은 방송 송출자가 OBS를 통해 RTMP 신호를 보내어 방송하는 트위치에서 개발자도구를 통해 확인한 요청입니다.

RTMP 신호를 HLS로 변환시 `.m3u8`의 인덱스 파일과 `.ts`의 동영상 파일로 변환됩니다.

그렇기에 최상단의 `#EXTM3U`를 통해 영상 파일이 HLS로 변환된다는 것을 확인할 수 있었습니다.

그렇기에 해당 프로젝트에서도 RTMP 신호를 NGNIX의 RTMP 모듈을 통해 HLS로 변환해주었습니다.

---

#### RTMP 신호 인증

RTMP 모듈은 신호 변환외에 `on_play`, `on_play_done`과 같은 기능을 제공하여 원하는 타이밍에 인증 요청을 다른 서버로 보낼 수 있습니다.

```nginx
# 방송 시작전 인증 to Manager 서버 (POST)
on_publish http://${MANAGER_SERVER:PORT}/api/v1/manager/live;

# 방송 시작 알림 to 내부 Media 서버 (POST)
on_play http://localhost:${MEDIA_SERVER_PORT}/live;

# 방송 종료 알림 to 내부 Media 서버 (POST)
on_done_play http://localhost:${MEDIA_SERVER_PORT}/end;

# 방송 종료시 to Manager 서버 (POST)
on_done_publish http://${MANAGER_SERVER:PORT}/api/v1/manager/end;
```

해당 프로젝트에서는 위와 같이 다른 서버에 요청을 보내고 있습니다.

---

### Kafka

#### CDN 서버로의 영상 전송

해당프로젝트에서 CDN 서버로의 영상 전송의 경우 Kafka를 통해 영상을 전송합니다.

![Group 1859](https://user-images.githubusercontent.com/102807742/218960155-ca1f8a5c-1c99-4267-a455-aa755b4d7a73.png)

해당 프로젝트에서는 기존 Origin 서버를 Kafka로 교체한 것입니다.

기존에 Origin에 비해 Kafka로 교체하면서 얻는 장점은 서버들 간의 결합을 줄일 수 있다는 것이라 생각합니다.

기존 Origin 서버에서는 CDN이 추가된다면 해당 CDN에 관한 정보를 Origin 서버가 알고 있어야 합니다.

또 Origin 서버가 추가될 때도 Media 서버가 해당 Origin 서버에 대한 정보를 알고 있어야 합니다.

하지만 Kafka로 이를 대체하면 Media 서버와 CDN은 Kafka 블로커만 알고 있으면 됩니다.

Media 서버는 Kafka로 영상에 대한 정보를 담은 `live-topic` 메시지를 전송하고 이 메시지가 필요한 CDN 서버는 `live-topic`을 구독하여 영상 정보를 담은 메시지를 받습니다.