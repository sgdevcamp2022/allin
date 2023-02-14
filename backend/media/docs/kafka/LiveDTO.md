## LiveDTO

방송을 송출하기 위해서는 영상 정보 이외에도 추가적인 정보가 필요합니다.

```java
public class LiveDTO implements Serializable {

    String index;

    String type;

    String tsFileName;

    byte[] video;

}
```

위는 Kafka를 통해 전송하는 LiveDTO입니다.

+ index
  + index는 index.m3u8 파일으로 영상 파일 순서에 관한 정보를 담고 있습니다.
  ```
  
  ```

+ type
  + media 서버는 영상을 hi, mid, low 3개의 화질로 인코딩합니다.
  + type은 어떤 화질에 대한 영상인지를 나타내는 정보입니다.

+ tsFileName
  + tsFileName은 .ts 파일명으로 이는 index.m3u8에 존재합니다.

+ video
  + video는 영상에 대한 정보입니다.


### Kafka

LiveDTO를 카프카를 통해 전달하는 방법은 두 가지입니다.

우선 아래와 같이 LiveDTO 객체를 전달하는 방법이 있습니다.

```java
@Bean
public KafkaTemplate<String, LiveDTO> liveKafkaTemplate() {
        return new KafkaTemplate<>(liveProducerFactory());
}

public class LiveSerializer implements Serializer<LiveDTO> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public byte[] serialize(String topic, LiveDTO data) {
        try {
            if (data == null){
                return null;
            }
            return objectMapper.writeValueAsBytes(data);
        } catch (Exception e) {
            throw new SerializationException("Error when serializing Live DTO");
        }
    }

    @Override
    public void close() {
    }
}
```

이를 위해서는 LiveDTO를 전달할 `KafkaTemplate<String, LiveDTO> liveKafkaTemplate()`이 필요합니다.

또 LiveDTO를 Serializer할 `LiveSerializer`가 필요합니다.

**객체 자체를 전달하면 Consumer에서 Parse할 필요가 없지만 Producer에서 객체의 정보를 변경한다면 Consumer에서도 객체의 정보를 변경해야한다는 의존성이 생기게 됩니다.**

```java
private final Gson gson;

@Async
public void broadCastLiveVideo(Map<String, Boolean> live, String key, String type)
        throws IOException, InterruptedException {
    ...
  LiveDTO liveDTO = new LiveDTO(index, type, tsFileName, tsVideo);
  
  String liveDTOJSON = gson.toJson(liveDTO);
  
  ...

]
```

**그렇기에 위처럼 객체가 아닌 LiveDTO를 Json으로 Stringfy하여 전송한다면 객체 타입이 아닌 String 타입으로 전송되기에  Consumer와 Producer와의 의존성을 줄일 수 있습니다.**


