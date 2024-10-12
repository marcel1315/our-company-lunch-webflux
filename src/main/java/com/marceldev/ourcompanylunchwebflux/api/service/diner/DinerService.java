package com.marceldev.ourcompanylunchwebflux.api.service.diner;

import com.marceldev.ourcompanylunchwebflux.api.controller.diner.dto.CreateDinerRequest;
import com.marceldev.ourcompanylunchwebflux.api.controller.diner.dto.CreateDinerResponse;
import com.marceldev.ourcompanylunchwebflux.domain.diner.DinerEntity;
import com.marceldev.ourcompanylunchwebflux.domain.diner.DinerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class DinerService {

  private final DinerRepository dinerRepository;

  public Mono<CreateDinerResponse> createDiner(CreateDinerRequest request) {
    DinerEntity dinerEntity = request.toDiner();
    return dinerRepository.save(dinerEntity)
        .map(CreateDinerResponse::of);
  }
}
