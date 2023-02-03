package com.example.chat.controller.api;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.controller.api.TopicApiController;
import com.example.dto.TopicCreateRequest;
import com.example.service.TopicService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(controllers = TopicApiController.class)
public class TopicApiControllerTest {

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private TopicService topicService;

  @Nested
  @DisplayName("create 메서드는")
  class DescribeCreate {

    @Nested
    @DisplayName("유효한 값이 전달되면")
    class ContextWithValidData {

      @Test
      @DisplayName("chatService.create()를 호출하고 200 상태코드를 응답한다")
      void ItCallsCreateAndReturns200() throws Exception {
        // given
        TopicCreateRequest request = new TopicCreateRequest("topic1",
          LocalDateTime.now().plusMinutes(15));

        // when, then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/topics")
                                              .contentType(MediaType.APPLICATION_JSON)
                                              .content(convertDataToJson(request)))
               .andExpect(status().isOk());
        verify(topicService).create(any(TopicCreateRequest.class));
      }

      @Nested
      @DisplayName("topicId가 빈 값이라")
      class ContextWithTopicIdEmpty {

        @ParameterizedTest
        @NullAndEmptySource
        @DisplayName("예외메시지를 반환한다")
        void ItReturnsExceptionResponse(String topicId) throws Exception {
          // given
          TopicCreateRequest request = new TopicCreateRequest(topicId,
            LocalDateTime.now().plusMinutes(15));

          // when, then
          MockHttpServletResponse res = mockMvc.perform(
                                                 MockMvcRequestBuilders.post("/api/v1/topics")
                                                                       .contentType(MediaType.APPLICATION_JSON)
                                                                       .content(convertDataToJson(request)))
                                               .andExpect(status().is4xxClientError())
                                               .andReturn().getResponse();
          res.getContentAsString().contains("topicId는 필수값입니다.");
        }
      }

      @Nested
      @DisplayName("expireAt가 null이라면")
      class ContextWithExpireAtNull {

        @Test
        @DisplayName("예외메시지를 반환한다")
        void ItReturnsExceptionResponse() throws Exception {
          // given
          TopicCreateRequest request = new TopicCreateRequest("topic1",
            null);

          // when, then
          MockHttpServletResponse res = mockMvc.perform(
                                                 MockMvcRequestBuilders.post("/api/v1/topics")
                                                                       .contentType(MediaType.APPLICATION_JSON)
                                                                       .content(convertDataToJson(request)))
                                               .andExpect(status().is4xxClientError())
                                               .andReturn().getResponse();
          res.getContentAsString().contains("expireAt은 필수값입니다.");
        }
      }
    }
  }

  private String convertDataToJson(Object data)
    throws JsonProcessingException {
    return objectMapper.writeValueAsString(data);
  }
}
