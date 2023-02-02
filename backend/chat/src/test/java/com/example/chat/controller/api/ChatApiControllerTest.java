package com.example.chat.controller.api;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.controller.api.ChatApiController;
import com.example.domain.Message;
import com.example.dto.ApiResponse;
import com.example.dto.ChatMessagePagingRequest;
import com.example.dto.ChatMessageResponse;
import com.example.service.ChatService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;

@AutoConfigureDataMongo
@WebMvcTest(controllers = ChatApiController.class)
class ChatApiControllerTest {

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private ChatService chatService;

  @Nested
  @DisplayName("findAll 메서드는")
  class DescribeFindAll {

    @Nested
    @DisplayName("유효한 값이 전달되면")
    class ContextWithValidData {

      @Test
      @DisplayName("채팅 메시지 목록을 반환한다")
      void ItReturnsChatMessageList() throws Exception {
        // given
        List<Message> messages = createChatMessage();
        List<ChatMessageResponse> chatMessages = messages.stream()
                                                         .map((m) -> ChatMessageResponse.of(
                                                           m.getSender(), m.getContent()))
                                                         .collect(Collectors.toList());
        String expectedResponse = objectMapper.writeValueAsString(new ApiResponse<>(chatMessages));
        given(chatService.findAll(any(ChatMessagePagingRequest.class)))
          .willReturn(chatMessages);

        // when, then
        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("page", "0");
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/chats")
                                              .params(params))
                                              .andExpect(status().isOk())
                                              .andExpect(content().json(expectedResponse));
      }
    }

    @Nested
    @DisplayName("페이지가 음수라면")
    class ContextWithPageNegative {

      @Test
      @DisplayName("예외 메시지를 반환한다")
      void ItThrowsExceptionResponse() throws Exception {
        // given
        // when, then
        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("page", "-1");
        MockHttpServletResponse res = mockMvc.perform(
                                               MockMvcRequestBuilders.get("/api/v1/chats")
                                                                     .params(params))
                                                                     .andExpect(status().is4xxClientError())
                                                                     .andReturn().getResponse();
        res.getContentAsString().contains("page는 양수이거나 0이어야합니다.");
      }
    }
  }

  private List<Message> createChatMessage() {
    List<Message> messages = new LinkedList<>();
    for (int i = 0; i < 20; i++) {
      Message message = Message.of("topic1", "마틴파울러", "hi~", LocalDateTime.now().plusMinutes(10));
      messages.add(message);
    }
    return messages;
  }
}
