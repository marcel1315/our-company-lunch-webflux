package com.marceldev.ourcompanylunchwebflux.api.service.operator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
public class CustomOperators {

  public static <T> Mono<Page<T>> paginate(Flux<T> items, Mono<Long> total, Pageable pageable) {
    return Mono.zip(total, items.collectList())
        .map(tuple ->
            new PageImpl<>(tuple.getT2(), pageable, tuple.getT1())
        );
  }
}
