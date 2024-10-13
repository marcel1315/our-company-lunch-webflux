package com.marceldev.ourcompanylunchwebflux.api.service.diner;

import static com.marceldev.ourcompanylunchwebflux.api.service.operator.CustomOperators.paginate;

import com.marceldev.ourcompanylunchwebflux.api.controller.diner.dto.CreateDinerRequest;
import com.marceldev.ourcompanylunchwebflux.api.controller.diner.dto.CreateDinerResponse;
import com.marceldev.ourcompanylunchwebflux.api.controller.diner.dto.GetDinerListRequest;
import com.marceldev.ourcompanylunchwebflux.api.controller.diner.dto.GetDinerListResponse;
import com.marceldev.ourcompanylunchwebflux.api.controller.diner.type.DinerSort;
import com.marceldev.ourcompanylunchwebflux.domain.diner.DinerEntity;
import com.marceldev.ourcompanylunchwebflux.domain.diner.DinerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
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

  public Mono<Page<GetDinerListResponse>> getDinerList(GetDinerListRequest request) {
    Pageable pageable = PageRequest.of(
        request.getPage(),
        request.getSize(),
        Sort.by(getOrder(request))
    );

    Mono<Long> total = dinerRepository.count();
    Flux<GetDinerListResponse> diners = dinerRepository.findAllBy(pageable)
        .map(GetDinerListResponse::of);

    return paginate(diners, total, pageable);
  }

  private Order getOrder(GetDinerListRequest request) {
    if (request.getSort() == DinerSort.DINER_NAME) {
      return Order.asc(request.getSort().getField());
    } else {
      return Order.desc(request.getSort().getField());
    }
  }
}

