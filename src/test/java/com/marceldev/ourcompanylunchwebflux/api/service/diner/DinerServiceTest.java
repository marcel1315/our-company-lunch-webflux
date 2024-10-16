package com.marceldev.ourcompanylunchwebflux.api.service.diner;

import static org.assertj.core.api.Assertions.assertThat;

import com.marceldev.ourcompanylunchwebflux.api.controller.diner.dto.CreateDinerRequest;
import com.marceldev.ourcompanylunchwebflux.api.controller.diner.dto.CreateDinerResponse;
import com.marceldev.ourcompanylunchwebflux.api.controller.diner.dto.GetDinerListRequest;
import com.marceldev.ourcompanylunchwebflux.api.controller.diner.dto.GetDinerListResponse;
import com.marceldev.ourcompanylunchwebflux.api.controller.diner.type.DinerSort;
import com.marceldev.ourcompanylunchwebflux.api.controller.exception.DinerNotFoundException;
import com.marceldev.ourcompanylunchwebflux.basic.IntegrationTest;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

class DinerServiceTest extends IntegrationTest {

  @AfterEach
  void tearDown() {
    dinerRepository.deleteAll().subscribe();
  }

  @Test
  @DisplayName("Create diner - Success")
  void create_diner() {
    // given
    CreateDinerRequest request = CreateDinerRequest.builder()
        .name("Frank fries")
        .link("www.frankfries.com")
        .build();

    // when
    Mono<CreateDinerResponse> diner = dinerService.createDiner(request);

    // then
    StepVerifier.create(diner.log())
        .assertNext(response -> {
          assertThat(response.getId()).isNotNull();
          assertThat(response.getName()).isEqualTo(request.getName());
          assertThat(response.getLink()).isEqualTo(request.getLink());
        })
        .verifyComplete();
  }

  @Test
  @DisplayName("Get diner list - Success(Name ordering)")
  void get_diner_list() {
    // given
    saveDiner(15);
    GetDinerListRequest request = createGetDinerListRequest(0, 10, DinerSort.DINER_NAME);

    // when
    Mono<Page<GetDinerListResponse>> diners = dinerService.getDinerList(request);

    // then
    StepVerifier.create(diners.log())
        .assertNext(response -> {
          assertThat(response.getContent()).hasSize(10);
          assertThat(response.getTotalElements()).isEqualTo(15);
        })
        .verifyComplete();
  }

  @Test
  @DisplayName("Get diner list - Success(Empty array when no diner is saved")
  void get_diner_list_no_diner() {
    // given
    GetDinerListRequest request = createGetDinerListRequest(0, 10, DinerSort.DINER_NAME);

    // when
    Mono<Page<GetDinerListResponse>> diners = dinerService.getDinerList(request);

    // then
    StepVerifier.create(diners.log())
        .assertNext(response -> {
          assertThat(response.getContent()).hasSize(0);
        })
        .verifyComplete();
  }

  @Test
  @DisplayName("Get diner list - Success(Name ordering)")
  void get_diner_list_name_ordering() {
    // given
    saveDiner(List.of("Hello Diner", "Burger Queen", "HiTaco"));
    GetDinerListRequest request = createGetDinerListRequest(0, 10, DinerSort.DINER_NAME);

    // when
    Mono<Page<GetDinerListResponse>> diners = dinerService.getDinerList(request);

    // then
    StepVerifier.create(diners.log())
        .assertNext(response -> {
          assertThat(response.getContent()).hasSize(3);
          assertThat(response.getContent()).extracting("name")
              .containsExactly("Burger Queen", "Hello Diner", "HiTaco");
        })
        .verifyComplete();
  }

  @Test
  @DisplayName("Get diner list - Success(Creation desc ordering)")
  void get_diner_list_creation_desc_ordering() {
    // given
    saveDiner(List.of("Hello Diner", "Burger Queen", "HiTaco"));
    GetDinerListRequest request = createGetDinerListRequest(0, 10, DinerSort.CREATED_AT);

    // when
    Mono<Page<GetDinerListResponse>> diners = dinerService.getDinerList(request);

    // then
    StepVerifier.create(diners.log())
        .assertNext(response -> {
          assertThat(response.getContent()).hasSize(3);
          assertThat(response.getContent()).extracting("name")
              .containsExactly("HiTaco", "Burger Queen", "Hello Diner");
        })
        .verifyComplete();
  }

  @Test
  @DisplayName("Delete diner - Success")
  void delete_diner() {
    // given
    CreateDinerResponse createDinerResponse = saveOneDiner();

    // when
    Mono<Void> response = dinerService.deleteDiner(createDinerResponse.getId());

    // then
    StepVerifier.create(response.log())
        .verifyComplete();
  }

  @Test
  @DisplayName("Delete diner - Fail(Diner not found with the diner id")
  void delete_diner_no_diner() {
    // given
    CreateDinerResponse createDinerResponse = saveOneDiner();
    dinerService.deleteDiner(createDinerResponse.getId()).subscribe();

    // when
    Mono<Void> response = dinerService.deleteDiner(createDinerResponse.getId());

    // then
    StepVerifier.create(response.log())
        .verifyError(DinerNotFoundException.class);
  }

  // --- Private create request ---

  private GetDinerListRequest createGetDinerListRequest(int page, int size, DinerSort dinerName) {
    return GetDinerListRequest.builder()
        .page(page)
        .size(size)
        .sort(dinerName)
        .build();
  }

  // --- Private save ---

  private CreateDinerResponse saveOneDiner() {
    CreateDinerRequest request = CreateDinerRequest.builder()
        .name("Frank fries")
        .link("www.frankfries.com")
        .build();
    return dinerService.createDiner(request).block();
  }

  private void saveDiner(int count) {
    Flux.range(0, count)
        .map(i -> CreateDinerRequest.builder()
            .name("Frank fries")
            .link("www.frankfries.com")
            .build()
        )
        .flatMap(request -> dinerService.createDiner(request))
        .subscribe();
  }

  private void saveDiner(List<String> names) {
    Flux.fromIterable(names)
        .map(name -> CreateDinerRequest.builder()
            .name(name)
            .link(String.format("www.%s.com", name.replace(" ", "")))
            .build()
        )
        .concatMap(request -> dinerService.createDiner(request))
        .subscribe();
  }
}