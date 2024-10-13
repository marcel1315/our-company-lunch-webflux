package com.marceldev.ourcompanylunchwebflux.api.controller.diner.type;

import lombok.Getter;

@Getter
public enum DinerSort {

  DINER_NAME("name"),
  CREATED_AT("created_at");

  private final String field;

  DinerSort(String field) {
    this.field = field;
  }
}
