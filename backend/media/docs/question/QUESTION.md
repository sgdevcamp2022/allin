## QUESTION

### 사소한 질문

```java
// LiveSender#broadCastLiveVideo

private final String HLS = "/hls";

private final String INDEX = "index";

private final String DASH = "/";

private final String UNDER_BAR = "_";

private final String DOT_M3U8 = ".m3u8";

private final String DOT_TS = ".ts";

BASE_URL + DASH + HLS + DASH + key + UNDER_BAR + type + DASH + INDEX + DOT_M3U8
```

하드 코딩을 하지 않기 위해서 상수로 만들어서 사용하는데 위의 예제와 같이 **과도한 느낌**이 드는 경우가 있습니다.

그렇더라도 하드 코딩을 하는 것 보다 상수로 선언하여 사용하는 것이 더 좋은 방법인지 궁금합니다.

---

### Async

````java

@Async
public void broadCastLiveVideo(Map<String, Boolean> live, String key, String type)
    throws IOException, InterruptedException {
    
    ...
    
    while (live.getOrDefault(key, true)) {

    String index = getM3U8(
    BASE_URL + DASH + HLS + DASH + key + UNDER_BAR + type + DASH + INDEX + DOT_M3U8);

    String tsFileName = substringLiveVideoFileName(index) + DOT_TS;

    byte[] tsVideo = getTsFile(
    BASE_URL + DASH + HLS + DASH + key + UNDER_BAR + type + DASH + tsFileName);

    LiveDTO liveDTO = new LiveDTO(index, type, tsFileName, tsVideo);

    String liveDTOJSON = gson.toJson(liveDTO);

    kafkaTemplate.send(
        TOPIC_LIVE,
        key,
        liveDTOJSON
    );

    TimeUnit.SECONDS.sleep(RENEWAL_TIME);
    }
}

@Async
public void broadCastLiveIndex(String key) throws IOException, InterruptedException {
    
}
````

위는 `@Async`를 적용한 메서드입니다.

`#broadCastLiveVideo`의 경우 `while(live.getOrDefault(key,true))`에서 확인할 수 있듯 

방송이 종료 요청이 올때까지 while 문을 속에서 반복됩니다.

이 반복 때문에 요청을 계속 잡아둘 수 없기 때문에 `@Async`를 통해 비동기 처리를 해주었습니다.

하지만 저는 아직 비동기에 관해서는 공부한 지식과 경험이 적습니다.

앞으로 비동기를 **공부할 방향성**이나 사용할 때 **주의해야할 사항**들에 대해서 궁금합니다.

(ex 쓰레드?, 예외처리?)