package com.marceldev.ourcompanylunchwebflux.domain.diner;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Table("diner")
public class DinerEntity {

  @Id
  private Long id;
  private final String name;
  private final String link;

  @CreatedDate
  private LocalDateTime createdAt;

  @PersistenceCreator
  public DinerEntity(Long id, String name, String link) {
    this.id = id;
    this.name = name;
    this.link = link;
  }

  @Builder
  public DinerEntity(String name, String link) {
    this.name = name;
    this.link = link;
  }
}
