package com.buildcomplete.examples.modularcqrsddd.domain.paymentprocessing;

import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.UUID;
import lombok.Value;

@Value
public class PaymentId {
  private final UUID value;

  public static PaymentId randomPaymentId() {
    return PaymentId.of(UUID.randomUUID());
  }

  @JsonCreator
  public static PaymentId of(UUID value) {
    return new PaymentId(value);
  }
}
