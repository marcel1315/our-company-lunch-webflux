package com.marceldev.ourcompanylunchwebflux.api.controller.diner.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class GetDinerListRequest {

  private final int page;
  private final int size;

  @Builder
  private GetDinerListRequest(int page, int size) {
    this.page = page;
    this.size = size;
  }
}
