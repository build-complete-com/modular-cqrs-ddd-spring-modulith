package com.buildcomplete.examples.modularcqrsddd.domain.paymentprocessing;

import com.buildcomplete.examples.modularcqrsddd.domain.framework.DomainEvent;
import lombok.Value;

@Value
public class PaymentStartedEvent extends DomainEvent {
    private final PaymentId paymentId;

    public PaymentStartedEvent(Payment payment) {
        this.paymentId = payment.getId();
    }
}
