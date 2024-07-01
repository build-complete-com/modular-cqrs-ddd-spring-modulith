package com.buildcomplete.examples.modularcqrsddd.orderprocessing.ports.events;

import java.util.UUID;
import lombok.Value;

@Value
public class OrderPayedPortEvent {
  UUID eventId;
  UUID orderId;
}
