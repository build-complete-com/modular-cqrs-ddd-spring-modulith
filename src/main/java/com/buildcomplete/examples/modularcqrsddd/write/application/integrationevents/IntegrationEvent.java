package com.buildcomplete.examples.modularcqrsddd.write.application.integrationevents;

import com.buildcomplete.examples.modularcqrsddd.domain.framework.DomainEvent;
import java.util.UUID;
import lombok.Value;

@Value
public class IntegrationEvent {
  private UUID messageGroupId;
  private DomainEvent domainEvent;
}
