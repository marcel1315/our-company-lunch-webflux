package com.marceldev.ourcompanylunchwebflux.basic;

import com.marceldev.ourcompanylunchwebflux.api.service.diner.DinerService;
import com.marceldev.ourcompanylunchwebflux.domain.diner.DinerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public abstract class IntegrationTest {

  // --- Repository ---

  @Autowired
  protected DinerRepository dinerRepository;

  // --- Service ---

  @Autowired
  protected DinerService dinerService;
}
