package com.buildcomplete.examples.modularcqrsddd.integrationevents;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class DomainEventPublishingIntegrationEventHandler implements IntegrationEventHandler {
  private final ApplicationEventPublisher applicationEventPublisher;

  @Override
  public void handleEvent(IntegrationEvent integrationEvent) {
    applicationEventPublisher.publishEvent(integrationEvent.getDomainEvent());
  }
}
