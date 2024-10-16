package com.marceldev.ourcompanylunchwebflux.api.controller.diner;

import com.marceldev.ourcompanylunchwebflux.api.controller.diner.dto.CreateDinerRequest;
import com.marceldev.ourcompanylunchwebflux.api.controller.diner.dto.CreateDinerResponse;
import com.marceldev.ourcompanylunchwebflux.api.controller.diner.dto.GetDinerListRequest;
import com.marceldev.ourcompanylunchwebflux.api.controller.diner.dto.GetDinerListResponse;
import com.marceldev.ourcompanylunchwebflux.api.controller.exception.DinerNotFoundException;
import com.marceldev.ourcompanylunchwebflux.api.controller.exception.ErrorResponse;
import com.marceldev.ourcompanylunchwebflux.api.service.diner.DinerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RequestMapping("/api/diners")
@RestController
public class DinerController {

  private final DinerService dinerService;

  @PostMapping
  public Mono<CreateDinerResponse> create(
      @RequestBody CreateDinerRequest request
  ) {
    return dinerService.createDiner(request);
  }

  @GetMapping
  public Mono<Page<GetDinerListResponse>> getDinerList(
      @ModelAttribute GetDinerListRequest request
  ) {
    return dinerService.getDinerList(request);
  }

  @DeleteMapping("/{dinerId}")
  public Mono<Void> deleteDiner(
      @PathVariable Long dinerId
  ) {
    return dinerService.deleteDiner(dinerId);
  }

  @ExceptionHandler
  public Mono<ResponseEntity<ErrorResponse>> dinerNotFound(DinerNotFoundException e) {
    return Mono.just(ErrorResponse.badRequest(3004, e.getMessage()));
  }
}
