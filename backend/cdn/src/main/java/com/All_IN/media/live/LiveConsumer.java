package com.All_IN.media.live;

import com.All_IN.media.live.dto.LiveDTO;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LiveConsumer {

    @Value("${cdn.base.path}")
    private String BASE_URL;

    private final String HLS = "hls";

    private final String INDEX_FILE_NAME = "index.m3u8";

    private final String DASH = "/";

    private final String UNDER_BAR = "_";


    private final LiveRemover liveRemover;


    @KafkaListener(topics = "live-topic", groupId = "live-group", containerFactory = "liveKafkaListenerContainerFactory")
    public void listenLive(@Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key, LiveDTO live) throws IOException, InterruptedException {

        String COMMON_URL = BASE_URL + DASH + HLS + DASH + key + UNDER_BAR + live.getType();
        String VIDEO_FILE = COMMON_URL + DASH + live.getTsFileName();
        String INDEX_FILE = COMMON_URL + DASH + INDEX_FILE_NAME;

        Files.write(Paths.get(VIDEO_FILE), live.getVideo());
        Files.write(Paths.get(INDEX_FILE), live.getIndex().getBytes());

        liveRemover.remove(VIDEO_FILE);
    }

}
