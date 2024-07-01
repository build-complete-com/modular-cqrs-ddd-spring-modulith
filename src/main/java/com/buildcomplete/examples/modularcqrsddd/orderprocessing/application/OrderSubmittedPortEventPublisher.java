package com.buildcomplete.examples.modularcqrsddd.orderprocessing.application;

import com.buildcomplete.examples.modularcqrsddd.orderprocessing.application.domain.OrderSubmittedEvent;
import com.buildcomplete.examples.modularcqrsddd.orderprocessing.ports.events.OrderSubmittedPortEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class OrderSubmittedPortEventPublisher {
  private final OrderSubmittedPortEventConverter portEventConverter;
  private final ApplicationEventPublisher eventPublisher;

  @EventListener
  void republishAsPortEvent(OrderSubmittedEvent domainEvent) {
    OrderSubmittedPortEvent eventDto = portEventConverter.convert(domainEvent);
    eventPublisher.publishEvent(eventDto);
  }
}
