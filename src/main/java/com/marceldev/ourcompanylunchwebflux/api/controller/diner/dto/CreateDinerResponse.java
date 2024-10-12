package com.marceldev.ourcompanylunchwebflux.api.controller.diner.dto;

import com.marceldev.ourcompanylunchwebflux.domain.diner.DinerEntity;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CreateDinerResponse {

  private final String name;
  private final String link;

  @Builder
  private CreateDinerResponse(String name, String link) {
    this.name = name;
    this.link = link;
  }

  public static CreateDinerResponse of(DinerEntity dinerEntity) {
    return CreateDinerResponse.builder()
        .name(dinerEntity.getName())
        .link(dinerEntity.getLink())
        .build();
  }
}
