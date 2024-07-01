package com.buildcomplete.examples.modularcqrsddd.orderprocessing.application;

import com.buildcomplete.examples.modularcqrsddd.domainsharedkernel.OrderPayedEvent;
import com.buildcomplete.examples.modularcqrsddd.orderprocessing.application.domain.OrderSubmittedEvent;
import com.buildcomplete.examples.modularcqrsddd.orderprocessing.ports.events.OrderPayedPortEvent;
import com.buildcomplete.examples.modularcqrsddd.orderprocessing.ports.events.OrderSubmittedPortEvent;
import org.springframework.stereotype.Service;

@Service
class OrderPayedPortEventConverter {

  OrderPayedPortEvent convert(OrderPayedEvent domainEvent) {
    return new OrderPayedPortEvent(domainEvent.getId(), domainEvent.getOrderId().getValue());
  }
}
