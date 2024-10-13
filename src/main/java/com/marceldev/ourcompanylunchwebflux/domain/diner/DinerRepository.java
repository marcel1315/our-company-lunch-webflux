package com.marceldev.ourcompanylunchwebflux.domain.diner;

import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;

public interface DinerRepository extends R2dbcRepository<DinerEntity, Long> {

  Flux<DinerEntity> findAllBy(Pageable pageable);
}
