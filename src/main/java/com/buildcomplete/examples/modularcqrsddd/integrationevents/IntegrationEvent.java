package com.buildcomplete.examples.modularcqrsddd.integrationevents;

import com.buildcomplete.examples.modularcqrsddd.domainframework.DomainEvent;
import java.util.UUID;
import lombok.Value;

@Value
public class IntegrationEvent {
  private UUID messageGroupId;
  private DomainEvent domainEvent;
}
