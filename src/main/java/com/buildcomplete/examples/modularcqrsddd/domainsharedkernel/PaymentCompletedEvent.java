package com.buildcomplete.examples.modularcqrsddd.domainsharedkernel;

import com.buildcomplete.examples.modularcqrsddd.domainframework.DomainEvent;
import lombok.Value;

@Value
public class PaymentCompletedEvent extends DomainEvent {
    private final PaymentId paymentId;
    private final OrderId orderId;

    public PaymentCompletedEvent(PaymentId paymentId, OrderId orderId) {
        this.paymentId = paymentId;
        this.orderId = orderId;
    }
}
