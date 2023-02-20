package com.All_IN.media.live;

import com.All_IN.media.live.dto.LiveDTO;
import com.google.gson.Gson;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LiveSender {

    @Value(value = "${media.base.path}")
    private String BASE_URL;

    private final String TOPIC_LIVE = "live-topic";

    private final String TOPIC_LIVE_INDEX = "liveIndex-topic";

    private final String HLS = "/hls";

    private final String INDEX = "index";

    private final String DASH = "/";

    private final String UNDER_BAR = "_";

    private final String DOT_M3U8 = ".m3u8";

    private final String DOT_TS = ".ts";

    private final Integer RENEWAL_TIME = 3;

    private final Integer SHORT_RENEWAL_TIME = 1;


    private final Gson gson;

    private final KafkaTemplate<String, String> kafkaTemplate;


    @Async
    public void broadCastLiveVideo(Map<String, Boolean> live, String key, String type)
        throws IOException, InterruptedException {

        String latestTsFileName = null;

        while (live.getOrDefault(key, true)) {

            String index = getM3U8(
                BASE_URL + DASH + HLS + DASH + key + UNDER_BAR + type + DASH + INDEX + DOT_M3U8);

            String tsFileName = substringLiveVideoFileName(index) + DOT_TS;

            byte[] tsVideo = getTsFile(
                BASE_URL + DASH + HLS + DASH + key + UNDER_BAR + type + DASH + tsFileName);

            LiveDTO liveDTO = new LiveDTO(index, type, tsFileName, tsVideo);

            String liveDTOJSON = gson.toJson(liveDTO);

            if (latestTsFileName != null) {
                if (!latestTsFileName.equals(tsFileName)) {
                    kafkaTemplate.send(
                        TOPIC_LIVE,
                        key,
                        liveDTOJSON
                    );
                    latestTsFileName = tsFileName;
                }
            } else {
                latestTsFileName = tsFileName;
            }
        }
    }

    private String substringLiveVideoFileName(String m3U8) {
        return m3U8.substring(m3U8.lastIndexOf(",") + 2, m3U8.lastIndexOf(DOT_TS));
    }

    private byte[] getTsFile(String path) throws IOException {
        return Files.readAllBytes(Paths.get(path));
    }

    @Async
    public void broadCastLiveIndex(String key) throws IOException, InterruptedException {
        String m3U8 = getM3U8(BASE_URL + DASH + HLS + DASH + key + DOT_M3U8);
        kafkaTemplate.send(TOPIC_LIVE_INDEX, key, m3U8);
    }

    private String getM3U8(String path) throws IOException, InterruptedException {
        byte[] m3u8 = null;

        while (m3u8 == null) {
            try {
                m3u8 = Files.readAllBytes(Paths.get(path));
            } catch (NoSuchFileException noSuchFileException) {
                TimeUnit.SECONDS.sleep(SHORT_RENEWAL_TIME);
            }
        }

        return new String(m3u8, StandardCharsets.UTF_8);
    }

}
