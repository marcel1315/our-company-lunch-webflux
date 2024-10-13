package com.marceldev.ourcompanylunchwebflux.api.service.operator;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class CustomOperators {

  public static <T> Mono<Page<T>> paginate(Flux<T> flux, Mono<Long> total, Pageable pageable) {
    return total
        .flatMap(count ->
            flux.collectList()
                .map(items -> new PageImpl<>(items, pageable, count))
        );
  }
}
