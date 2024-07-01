package com.buildcomplete.examples.modularcqrsddd.paymentprocessing.application.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;
import lombok.Value;

@Value
public class PaymentId {
  private final UUID value;

  public static PaymentId randomPaymentId() {
    return PaymentId.of(UUID.randomUUID());
  }

  @JsonCreator
  public static PaymentId of(@JsonProperty("value") UUID value) {
    return new PaymentId(value);
  }
}
