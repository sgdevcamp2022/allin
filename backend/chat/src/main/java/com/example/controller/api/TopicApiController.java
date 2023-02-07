package com.example.controller.api;

import com.example.dto.TopicCreateRequest;
import com.example.service.TopicService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/topics")
public class TopicApiController {

  private final TopicService topicService;

  /**
   * 미디어 서버와 통신용 api
   */
  @PostMapping
  void create(@RequestBody @Valid TopicCreateRequest request) {
    topicService.create(request);
  }
}
