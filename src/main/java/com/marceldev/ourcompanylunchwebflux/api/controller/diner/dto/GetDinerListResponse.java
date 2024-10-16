package com.marceldev.ourcompanylunchwebflux.api.controller.diner.dto;

import com.marceldev.ourcompanylunchwebflux.domain.diner.DinerEntity;
import lombok.Builder;
import lombok.Getter;

@Getter
public class GetDinerListResponse {

  private final Long id;
  private final String name;
  private final String link;

  @Builder
  private GetDinerListResponse(Long id, String name, String link) {
    this.id = id;
    this.name = name;
    this.link = link;
  }

  public static GetDinerListResponse of(DinerEntity dinerEntity) {
    return GetDinerListResponse.builder()
        .id(dinerEntity.getId())
        .name(dinerEntity.getName())
        .link(dinerEntity.getLink())
        .build();
  }
}
