package com.marceldev.ourcompanylunchwebflux.api.controller.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler
  public Mono<ResponseEntity<ErrorResponse>> handle(Exception e, ServerWebExchange request) {
    log.error("Exception, {}, {}, {}", request.getRequest().getURI(), e.getMessage(),
        String.valueOf(e.getCause()));

    return Mono.just(ErrorResponse.serverError(9000, "unknown"));
  }
}
