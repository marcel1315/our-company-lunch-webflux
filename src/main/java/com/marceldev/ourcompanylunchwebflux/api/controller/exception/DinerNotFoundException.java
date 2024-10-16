package com.marceldev.ourcompanylunchwebflux.api.controller.exception;

public class DinerNotFoundException extends RuntimeException {

  public DinerNotFoundException(Long id) {
    super(String.format("Diner not found: %s", id));
  }
}
