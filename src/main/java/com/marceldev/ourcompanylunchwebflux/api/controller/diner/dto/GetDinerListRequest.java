package com.marceldev.ourcompanylunchwebflux.api.controller.diner.dto;

import com.marceldev.ourcompanylunchwebflux.api.controller.diner.type.DinerSort;
import lombok.Builder;
import lombok.Getter;

@Getter
public class GetDinerListRequest {

  private final int page;
  private final int size;
  private final DinerSort sort;

  @Builder
  private GetDinerListRequest(int page, int size, DinerSort sort) {
    this.page = page;
    this.size = size;
    this.sort = sort;
  }
}
