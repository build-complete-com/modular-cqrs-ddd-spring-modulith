package com.buildcomplete.examples.modularcqrsddd.paymentprocessing.ports.repository;

import java.util.UUID;
import lombok.Value;

@Value
public class PaymentDto {
  UUID id;
  UUID orderId;
  String brokerPaymentId;
  boolean complete;
  Long version;
}
