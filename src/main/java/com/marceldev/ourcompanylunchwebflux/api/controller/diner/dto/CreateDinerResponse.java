package com.marceldev.ourcompanylunchwebflux.api.controller.diner.dto;

import com.marceldev.ourcompanylunchwebflux.domain.diner.Diner;
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

  public static CreateDinerResponse of(Diner diner) {
    return CreateDinerResponse.builder()
        .name(diner.getName())
        .link(diner.getLink())
        .build();
  }
}
