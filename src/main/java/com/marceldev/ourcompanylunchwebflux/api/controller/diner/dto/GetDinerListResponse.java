package com.marceldev.ourcompanylunchwebflux.api.controller.diner.dto;

import com.marceldev.ourcompanylunchwebflux.domain.diner.DinerEntity;
import lombok.Builder;
import lombok.Getter;

@Getter
public class GetDinerListResponse {

  private final String name;
  private final String link;

  @Builder
  private GetDinerListResponse(String name, String link) {
    this.name = name;
    this.link = link;
  }

  public static GetDinerListResponse of(DinerEntity dinerEntity) {
    return GetDinerListResponse.builder()
        .name(dinerEntity.getName())
        .link(dinerEntity.getLink())
        .build();
  }
}
