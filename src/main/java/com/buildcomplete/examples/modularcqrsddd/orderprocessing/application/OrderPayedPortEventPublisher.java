package com.buildcomplete.examples.modularcqrsddd.orderprocessing.application;

import com.buildcomplete.examples.modularcqrsddd.orderprocessing.application.domain.OrderPayedEvent;
import com.buildcomplete.examples.modularcqrsddd.orderprocessing.ports.events.OrderPayedPortEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class OrderPayedPortEventPublisher {
  private final OrderPayedPortEventConverter portEventConverter;
  private final ApplicationEventPublisher eventPublisher;

  @EventListener
  void republishAsPortEvent(OrderPayedEvent domainEvent) {
    OrderPayedPortEvent eventDto = portEventConverter.convert(domainEvent);
    eventPublisher.publishEvent(eventDto);
  }
}
