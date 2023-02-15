package com.example.controller.api;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.common.ReportReason;
import com.example.dto.ReportRequest;
import com.example.service.ReportService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

@WebMvcTest(controllers = ReportApiController.class)
class ReportApiControllerTest {

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private ReportService reportService;


  @Nested
  @DisplayName("report 메서드는")
  class DescribeReport {

    @Nested
    @DisplayName("reportedUser가 빈 값이라면")
    class ContextWithReportedUserEmpty {

      @ParameterizedTest
      @NullAndEmptySource
      @DisplayName("예외 메시지를 반환한다")
      void ItReturnsExceptionResponse(String reportedUser) throws Exception {
        // given

        // when, then
        ReportRequest reportRequest = new ReportRequest("topic1", reportedUser, "user1", "별로에요",
          ReportReason.UNPLEASANT_EXPRESSION);

        MockHttpServletResponse res = mockMvc.perform(
                                               MockMvcRequestBuilders.post("/api/v1/reports")
                                                                     .contentType(MediaType.APPLICATION_JSON)
                                                                     .content(convertDataToJson(reportRequest)))
                                             .andExpect(status().is4xxClientError())
                                             .andReturn().getResponse();
        res.getContentAsString().contains("요청 데이터가 올바르지 않습니다.");
      }
    }

    @Nested
    @DisplayName("reportedUser가 8자를 넘는다면")
    class ContextWithReportedUserLengthOver8 {

      @Test
      @DisplayName("예외 메시지를 반환한다")
      void ItReturnsExceptionResponse() throws Exception {
        // given

        // when, then
        ReportRequest reportRequest = new ReportRequest("topic1", "user2", "r".repeat(9), "별로에요",
          ReportReason.UNPLEASANT_EXPRESSION);

        MockHttpServletResponse res = mockMvc.perform(
                                               MockMvcRequestBuilders.post("/api/v1/reports")
                                                                     .contentType(MediaType.APPLICATION_JSON)
                                                                     .content(convertDataToJson(reportRequest)))
                                             .andExpect(status().is4xxClientError())
                                             .andReturn().getResponse();
        res.getContentAsString().contains("요청 데이터가 올바르지 않습니다.");
      }
    }

    @Nested
    @DisplayName("reporter가 빈 값이라면")
    class ContextWithReporterEmpty {

      @ParameterizedTest
      @NullAndEmptySource
      @DisplayName("예외 메시지를 반환한다")
      void ItReturnsExceptionResponse(String reporter) throws Exception {
        // given

        // when, then
        ReportRequest reportRequest = new ReportRequest("topic1", "user1", reporter, "별로에요",
          ReportReason.UNPLEASANT_EXPRESSION);

        MockHttpServletResponse res = mockMvc.perform(
                                               MockMvcRequestBuilders.post("/api/v1/reports")
                                                                     .contentType(MediaType.APPLICATION_JSON)
                                                                     .content(convertDataToJson(reportRequest)))
                                             .andExpect(status().is4xxClientError())
                                             .andReturn().getResponse();
        res.getContentAsString().contains("요청 데이터가 올바르지 않습니다.");
      }
    }

    @Nested
    @DisplayName("reporter가 8자를 넘는다면")
    class ContextWithReporterLengthOver8 {

      @Test
      @DisplayName("예외 메시지를 반환한다")
      void ItReturnsExceptionResponse() throws Exception {
        // given

        // when, then
        ReportRequest reportRequest = new ReportRequest("topic1", "user2", "r".repeat(9), "별로에요",
          ReportReason.UNPLEASANT_EXPRESSION);

        MockHttpServletResponse res = mockMvc.perform(
                                               MockMvcRequestBuilders.post("/api/v1/reports")
                                                                     .contentType(MediaType.APPLICATION_JSON)
                                                                     .content(convertDataToJson(reportRequest)))
                                             .andExpect(status().is4xxClientError())
                                             .andReturn().getResponse();
        res.getContentAsString().contains("요청 데이터가 올바르지 않습니다.");
      }
    }

    @Nested
    @DisplayName("message가 빈 값이라면")
    class ContextWithMessageEmpty {

      @ParameterizedTest
      @NullAndEmptySource
      @DisplayName("예외 메시지를 반환한다")
      void ItReturnsExceptionResponse(String message) throws Exception {
        // given

        // when, then
        ReportRequest reportRequest = new ReportRequest("topic1", "user3", "user1", message,
          ReportReason.UNPLEASANT_EXPRESSION);

        MockHttpServletResponse res = mockMvc.perform(
                                               MockMvcRequestBuilders.post("/api/v1/reports")
                                                                     .contentType(MediaType.APPLICATION_JSON)
                                                                     .content(convertDataToJson(reportRequest)))
                                             .andExpect(status().is4xxClientError())
                                             .andReturn().getResponse();
        res.getContentAsString().contains("요청 데이터가 올바르지 않습니다.");
      }
    }

    @Nested
    @DisplayName("message가 100자를 넘는다면")
    class ContextWithMessageLengthOver100 {

      @Test
      @DisplayName("예외 메시지를 반환한다")
      void ItReturnsExceptionResponse() throws Exception {
        // given

        // when, then
        ReportRequest reportRequest = new ReportRequest("topic1", "usre2", "user1", "r".repeat(101),
          ReportReason.UNPLEASANT_EXPRESSION);

        MockHttpServletResponse res = mockMvc.perform(
                                               MockMvcRequestBuilders.post("/api/v1/reports")
                                                                     .contentType(MediaType.APPLICATION_JSON)
                                                                     .content(convertDataToJson(reportRequest)))
                                             .andExpect(status().is4xxClientError())
                                             .andReturn().getResponse();
        res.getContentAsString().contains("요청 데이터가 올바르지 않습니다.");
      }
    }


    @Nested
    @DisplayName("reason이 null이라면")
    class ContextWithReasonNull {

      @Test
      @DisplayName("예외 메시지를 반환한다")
      void ItReturnsExceptionResponse() throws Exception {
        // given

        // when, then
        ReportRequest reportRequest = new ReportRequest("topic1", "usre2", "user1", "r".repeat(101),
          null);

        MockHttpServletResponse res = mockMvc.perform(
                                               MockMvcRequestBuilders.post("/api/v1/reports")
                                                                     .contentType(MediaType.APPLICATION_JSON)
                                                                     .content(convertDataToJson(reportRequest)))
                                             .andExpect(status().is4xxClientError())
                                             .andReturn().getResponse();
        res.getContentAsString().contains("요청 데이터가 올바르지 않습니다.");
      }
    }

    @Nested
    @DisplayName("topicId가 빈 값이라면")
    class ContextWithTopicIdEmpty {

      @ParameterizedTest
      @NullAndEmptySource
      @DisplayName("예외 메시지를 반환한다")
      void ItReturnsExceptionResponse(String topicId) throws Exception {
        // given

        // when, then
        ReportRequest reportRequest = new ReportRequest(topicId, "user2", "user1", "별로에요",
          ReportReason.UNPLEASANT_EXPRESSION);

        MockHttpServletResponse res = mockMvc.perform(
                                               MockMvcRequestBuilders.post("/api/v1/reports")
                                                                     .contentType(MediaType.APPLICATION_JSON)
                                                                     .content(convertDataToJson(reportRequest)))
                                             .andExpect(status().is4xxClientError())
                                             .andReturn().getResponse();
        res.getContentAsString().contains("요청 데이터가 올바르지 않습니다.");
      }
    }
  }

  private String convertDataToJson(Object data)
    throws JsonProcessingException {
    return objectMapper.writeValueAsString(data);
  }
}
