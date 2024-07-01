package com.buildcomplete.examples.modularcqrsddd.paymentprocessing.ports.events;

import java.util.UUID;
import lombok.Value;

@Value
public class BrokerPaymentIdAssignedPortEvent {
  UUID paymentId;
  String brokerPaymentId;
}
