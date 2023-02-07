package com.All_IN.media.live;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Component
public class M3u8Consumer {

    @Value("${cdn.base.path}")
    private String BASE_URL;

    private final String DOT_M3U8 = ".m3u8";

    private final String DASH = "/";


    @KafkaListener(topics = "liveIndex-topic", groupId = "live-group", containerFactory = "kafkaListenerContainerFactory")
    public void listenM3U8(@Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key, String index) throws IOException {

        Files.write(Paths.get(BASE_URL + DASH + key + DOT_M3U8), index.getBytes());
    }

}
