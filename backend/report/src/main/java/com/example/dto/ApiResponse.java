package com.example.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ApiResponse<T> {

  private static final String result = "success";
  private final T data;

  public static ApiResponse withEmptyData() {
    return new ApiResponse(null);
  }
}
