package com.All_IN.manager.web.api.v1;

import com.All_IN.manager.domain.publisher.Publisher;
import com.All_IN.manager.service.braodCast.BroadCastService;
import com.All_IN.manager.service.publisher.PublisherService;
import com.All_IN.manager.service.publisher.PublisherValidateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/manager")
public class ManagerController {

    private final PublisherService publisherService;

    private final PublisherValidateService publisherValidateService;

    private final BroadCastService broadCastService;


    @PostMapping("/live")
    public void onLive(@RequestParam String name, @RequestParam String pw) {
        Publisher publisher = publisherValidateService.validatePublisher(name, pw);

        publisherService.usePassword(pw);

        broadCastService.startLive(publisher);
    }

    @PostMapping("/end")
    public void endLive(@RequestParam String name) {
        Publisher publisher = publisherValidateService.validatePublisher(name);

        broadCastService.endLive(publisher);
    }

}
