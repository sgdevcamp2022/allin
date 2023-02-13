package com.All_IN.media.live;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.TimeUnit;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class LiveRemover {

    private final Integer RENEWAL_TIME = 9;


    @Async
    public void remove(String file) throws IOException, InterruptedException {
        TimeUnit.SECONDS.sleep(RENEWAL_TIME);

        Files.deleteIfExists(Path.of(file));
    }

}
