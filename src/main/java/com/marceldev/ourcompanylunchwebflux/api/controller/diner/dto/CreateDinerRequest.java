package com.marceldev.ourcompanylunchwebflux.api.controller.diner.dto;

import com.marceldev.ourcompanylunchwebflux.domain.diner.Diner;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CreateDinerRequest {

  private final String name;
  private final String link;

  public Diner toDiner() {
    return Diner.builder()
        .name(name)
        .link(link)
        .build();
  }
}
