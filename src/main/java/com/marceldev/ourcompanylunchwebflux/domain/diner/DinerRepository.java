package com.marceldev.ourcompanylunchwebflux.domain.diner;

import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class DinerRepository {

  // TODO: Bring persistence
  public Mono<Diner> save(Diner diner) {
    return Mono.just(diner);
  }
}
