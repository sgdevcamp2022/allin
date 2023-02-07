package com.All_IN.media.live;

import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LiveController {

    private final LiveSendService liveSendService;

    @PostMapping("/live")
    public void onLive(@RequestParam String name) throws IOException, InterruptedException {
        liveSendService.onLive(name);
    }

    @PostMapping("/end")
    public void endLive(@RequestParam String name) {
        liveSendService.endLive(name);
    }

}
