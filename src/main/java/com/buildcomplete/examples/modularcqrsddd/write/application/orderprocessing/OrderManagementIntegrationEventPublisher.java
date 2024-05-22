package com.buildcomplete.examples.modularcqrsddd.write.application.orderprocessing;

import com.buildcomplete.examples.modularcqrsddd.domain.orderprocessing.OrderSubmittedEvent;
import com.buildcomplete.examples.modularcqrsddd.write.application.integrationevents.IntegrationEvent;
import com.buildcomplete.examples.modularcqrsddd.write.application.integrationevents.IntegrationEventPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class OrderManagementIntegrationEventPublisher {
  private final IntegrationEventPublisher integrationEventPublisher;

  @EventListener({OrderSubmittedEvent.class})
  void republishAsIntegrationEvents(OrderSubmittedEvent domainEvent) {
    IntegrationEvent integrationEvent = new IntegrationEvent(domainEvent.getOrderId().getValue(), domainEvent);
    integrationEventPublisher.publishEvent(integrationEvent);
  }
}
