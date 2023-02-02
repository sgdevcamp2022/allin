package com.example.service;

import com.example.dto.ChatMessagePagingRequest;
import com.example.dto.ChatMessageRequest;
import com.example.dto.ChatMessageResponse;
import java.util.List;

public interface ChatService {

  void send(String id, ChatMessageRequest message);
  List<ChatMessageResponse> findAll(String topicId, ChatMessagePagingRequest request);
}
