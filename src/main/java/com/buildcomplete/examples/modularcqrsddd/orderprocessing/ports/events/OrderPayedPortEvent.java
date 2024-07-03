package com.buildcomplete.examples.modularcqrsddd.orderprocessing.ports.events;

import java.util.UUID;
import lombok.Value;
import org.springframework.modulith.events.Externalized;

@Externalized("modular-monolith-topic.fifo::#{#this.getOrderId()}")
@Value
public class OrderPayedPortEvent {
  UUID eventId;
  UUID orderId;
}
