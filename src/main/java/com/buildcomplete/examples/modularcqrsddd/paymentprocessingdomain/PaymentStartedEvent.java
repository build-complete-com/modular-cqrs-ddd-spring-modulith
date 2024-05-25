package com.buildcomplete.examples.modularcqrsddd.paymentprocessingdomain;

import com.buildcomplete.examples.modularcqrsddd.domainframework.DomainEvent;
import com.buildcomplete.examples.modularcqrsddd.domainsharedkernel.PaymentId;
import lombok.Value;

@Value
public class PaymentStartedEvent extends DomainEvent {
    private final PaymentId paymentId;

    public PaymentStartedEvent(Payment payment) {
        this.paymentId = payment.getId();
    }
}
