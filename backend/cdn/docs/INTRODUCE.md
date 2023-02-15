## INTRODUCE

간단 소개에서 설명하였듯 CDN 서버는 Kafka 브로커로부터 영상에 관한 메시지를 받아서 시청자에게 제공할 영상을 캐싱합니다.


![Group 1857](https://user-images.githubusercontent.com/102807742/218959914-c94d309e-8ef6-4c4e-b357-39f2a184fc07.png)

위 아키텍처에서 CDN 서버가 없다면 시청자의 요청과 방송 송출자의 요청이 **모두 Origin 서버에 집중**되게 됩니다.

하지만 CDN 서버가 있으면 시청자의 요청이 많이지는 경우에 CDN 서버를 추가로 구축하여 여러 CDN 서버로 **시청자의 요청을 분산**할 수 있습니다.

그렇기에 CDN 서버를 구축하는데 **연관관계가 적고 빠르게 구축할 수 있으면 좋을 것**이라 판단하였습니다.

### Kafka

```
rtmp {
    server {
        listen 1935;
        application live {
            live on;
            record off;

            push rtmp://{origin or cdn ip 주소}/stream명/stream key;
            push rtmp://{origin or cdn ip 주소}/stream명/stream key;
            push rtmp://{origin or cdn ip 주소}/stream명/stream key;
            ...
        }
}
```

위는 NGINIX RTMP를 사용하여 Media 서버에서 Origin 서버 혹은 Origin 서버에서 CDN 서버로 **RTMP를 릴레이** 하는 설정입니다.

저는 위의 설정에서 `{origin or cdn ip 주소}`를 요구하기에 **서버들간에 강한 연관관계**가 생긴다는 것을 파악하였습니다.

저는 이 연관관계를 끊은 방법을 생각하였고 **NGINX RTMP의 Origin 서버를 Kafka로 대체하는 방법**을 생각하였습니다.

기존에는 CDN 서버를 추가하기 위해서는 CDN 서버를 추가하고 Origin 서버 설정까지 수정하여야 했다면

Kafka를 도입한 이후에는 CDN 서버를 추가하고 **Origin 서버를 대체한 Kafka 브로커의 주소만 CDN 서버에 알려주면 되기에**

**유연하고 빠른 CDN 서버 구축이 가능질 것이라 생각합니다.**