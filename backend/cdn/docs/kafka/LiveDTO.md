## LiveDTO

### Kafka
[Media 서버의 LiveDTO](https://github.com/belljun3395/allin/blob/belljun/backend/media/docs/kafka/LiveDTO.md)에서 LiveDTO를 어떻게 Kafka를 통해 전송하는지에 관하여 설명 하였습니다.

CDN 서버에서는 LiveDTO를 Kafka에서 어떻게 받는지에 관하여 설명하도록 하겠습니다.

Kafka를 통해 객체를 보내는 방법에 따라 받는 방법도 다릅니다.

우선 아래는 **객체 형태로 메시지를 보내는 경우 이를 받는 방법**입니다.

```java
@Bean
public ConcurrentKafkaListenerContainerFactory<String, Object> liveKafkaListenerContainerFactory() {
    ConcurrentKafkaListenerContainerFactory<String, Object> factory =
        new ConcurrentKafkaListenerContainerFactory<>();

    factory.setConsumerFactory(liveConsumerFactory());

    return factory;
}

public class LiveDeserializer implements Deserializer<LiveDTO> {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public LiveDTO deserialize(String topic, byte[] data) {
        try {
            if (data == null){
                return null;
            }
            return objectMapper.readValue(new String(data, "UTF-8"), LiveDTO.class);
        } catch (Exception e) {
            throw new SerializationException("Error when deserializing byte[] to Live DTO");
        }
    }

    @Override
    public void close() {
    }
}
```

이를 위해서는 LiveDTO 객체를 포함한 메시지를 받을 

`ConcurrentKafkaListenerContainerFactory<String, Object> liveKafkaListenerContainerFactory()`가 필요합니다.

또 LiveDTO를 Deserialize 할 `LiveDeserializer`가 필요합니다.

`LiveDeserializer`에서 `objectMapper.readValue(new String(data, "UTF-8"), LiveDTO.class)`와 같이 객체를 Deserialize 하기에 

**전송하는 객체가 수정되면 받는 객체도 동일하게 수정하여야 하는 의존성을 확인할 수 있습니다.**

```java
private final Gson gson;

@KafkaListener(topics = "live-topic", groupId = "live-group", containerFactory = "kafkaListenerContainerFactory")
public void listenLive(@Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key, String liveDTOJSON) throws IOException, InterruptedException{
    LiveDTO live = gson.fromJson(liveDTOJSON,LiveDTO.class);
    ...
    }
```

하지만 위의 코드처럼 **Json으로 Stringify된 객체를 받는 경우** Gson을 통해 Json을 Parse하는 것을 확인할 수 있습니다.

전송하는 객체가 수정되면 받는 객체도 동일하게 수정하여야 하는 객체를 전송할 때와 달리 **전송 객체에 대한 의존성을 줄일 수 있습니다.**

또 `containerFactory = "kafkaListenerContainerFactory"`를 통해 알 수 있듯 `KafkaListenerContainerFactory`도 

`ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory()`로 가장 기본적인 설정을 사용하여

추가적인 `KafkaListenerContainerFactory` 구현이 없어도 된다는 장점이 있습니다.
