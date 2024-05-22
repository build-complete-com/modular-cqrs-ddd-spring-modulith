package com.buildcomplete.examples.modularcqrsddd.domain.sharedkernel;

import com.buildcomplete.examples.modularcqrsddd.domain.framework.DomainEvent;
import com.buildcomplete.examples.modularcqrsddd.domain.paymentprocessing.Payment;
import com.buildcomplete.examples.modularcqrsddd.domain.paymentprocessing.PaymentId;
import lombok.Value;

@Value
public class PaymentCompletedEvent extends DomainEvent {
    private final PaymentId paymentId;
    private final OrderId orderId;

    public PaymentCompletedEvent(Payment payment) {
        this.paymentId = payment.getId();
        this.orderId = payment.getOrderId();
    }
}
