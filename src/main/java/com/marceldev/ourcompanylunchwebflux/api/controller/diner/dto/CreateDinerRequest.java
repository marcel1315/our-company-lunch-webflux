package com.marceldev.ourcompanylunchwebflux.api.controller.diner.dto;

import com.marceldev.ourcompanylunchwebflux.domain.diner.DinerEntity;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CreateDinerRequest {

  private final String name;
  private final String link;

  public DinerEntity toDiner() {
    return DinerEntity.builder()
        .name(name)
        .link(link)
        .build();
  }

  @Builder
  private CreateDinerRequest(String name, String link) {
    this.name = name;
    this.link = link;
  }
}
