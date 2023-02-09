package com.All_IN.media.live;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LiveSendService {
    private final String HI = "hi";

    private final String LOW = "low";

    private final String MID = "mid";


    private Map<String, Boolean> live = new HashMap<>();


    private final LiveSender liveSender;


    @Async
    public void onLive(String key) throws IOException, InterruptedException {
        liveSender.broadCastLiveIndex(key);

        liveSender.broadCastLiveVideo(live, key, HI);
        liveSender.broadCastLiveVideo(live, key, LOW);
        liveSender.broadCastLiveVideo(live, key, MID);
    }

    public void endLive(String key) {
        live.put(key, false);
    }

}
