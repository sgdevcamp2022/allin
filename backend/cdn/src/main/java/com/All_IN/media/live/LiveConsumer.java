package com.All_IN.media.live;

import com.All_IN.media.live.dto.LiveDTO;
import com.google.gson.Gson;
import java.io.File;
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

    private final String INDEX_FILE_NAME = "index.m3u8";

    private final String HLS = "hls";

    private final String DASH = "/";

    private final String UNDER_BAR = "_";


    private final Gson gson;
    
    private final LiveRemover liveRemover;


    @KafkaListener(topics = "live-topic", groupId = "live-group", containerFactory = "kafkaListenerContainerFactory")
    public void listenLive(@Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key, String liveDTOJSON) throws IOException, InterruptedException {
        LiveDTO live = gson.fromJson(liveDTOJSON, LiveDTO.class);

        String COMMON_URL = BASE_URL + DASH + HLS + DASH + key + UNDER_BAR + live.getType();
        String VIDEO_FILE = COMMON_URL + DASH + live.getTsFileName();
        String INDEX_FILE = COMMON_URL + DASH + INDEX_FILE_NAME;

        File Folder = new File(COMMON_URL);

        if (!Folder.exists()) {
            try{
                Folder.mkdir();
            }
            catch(Exception e){
                e.getStackTrace();
            }
        }

        Files.write(Paths.get(VIDEO_FILE), live.getVideo());
        Files.write(Paths.get(INDEX_FILE), live.getIndex().getBytes());

        liveRemover.remove(VIDEO_FILE);
    }

}
