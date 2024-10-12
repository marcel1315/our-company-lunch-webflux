package com.marceldev.ourcompanylunchwebflux.domain.diner;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Diner {

  private final String name;
  private final String link;

  @Builder
  public Diner(String name, String link) {
    this.name = name;
    this.link = link;
  }
}
