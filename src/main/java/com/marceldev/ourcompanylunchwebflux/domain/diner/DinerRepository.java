package com.marceldev.ourcompanylunchwebflux.domain.diner;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface DinerRepository extends R2dbcRepository<DinerEntity, Long> {

}
