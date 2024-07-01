package com.buildcomplete.examples.modularcqrsddd.orderprocessing.application;

import com.buildcomplete.examples.modularcqrsddd.orderprocessing.application.domain.OrderPayedEvent;
import com.buildcomplete.examples.modularcqrsddd.orderprocessing.ports.events.OrderPayedPortEvent;
import org.springframework.stereotype.Service;

@Service
class OrderPayedPortEventConverter {

  OrderPayedPortEvent convert(OrderPayedEvent domainEvent) {
    return new OrderPayedPortEvent(domainEvent.getId(), domainEvent.getOrderId().getValue());
  }
}
