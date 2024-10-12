package com.marceldev.ourcompanylunchwebflux.api.controller.diner.dto;

import com.marceldev.ourcompanylunchwebflux.domain.diner.DinerEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CreateDinerRequest {

  private final String name;
  private final String link;

  public DinerEntity toDiner() {
    return DinerEntity.builder()
        .name(name)
        .link(link)
        .build();
  }
}
