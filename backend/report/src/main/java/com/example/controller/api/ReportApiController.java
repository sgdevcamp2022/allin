package com.example.controller.api;

import com.example.dto.ApiResponse;
import com.example.dto.ReportRequest;
import com.example.service.ReportService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/reports")
public class ReportApiController {

  private final ReportService reportService;

  @PostMapping
  ApiResponse report(@RequestBody @Valid ReportRequest request) {
    reportService.report(request);
    return ApiResponse.withEmptyData();
  }
}
