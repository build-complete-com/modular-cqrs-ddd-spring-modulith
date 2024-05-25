package com.buildcomplete.examples.modularcqrsddd.domainframework;

import java.util.UUID;
import lombok.Value;
import lombok.experimental.NonFinal;

@Value
@NonFinal
public class DomainEvent {
  private final UUID id;

  public DomainEvent(UUID id) {
    this.id = id;
  }

  public DomainEvent() {
    this.id = UUID.randomUUID();
  }
}
