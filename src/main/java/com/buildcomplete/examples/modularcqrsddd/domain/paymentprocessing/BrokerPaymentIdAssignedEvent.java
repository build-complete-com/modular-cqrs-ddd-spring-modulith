package com.buildcomplete.examples.modularcqrsddd.domain.paymentprocessing;

import com.buildcomplete.examples.modularcqrsddd.domain.framework.DomainEvent;
import lombok.Value;

@Value
public class BrokerPaymentIdAssignedEvent extends DomainEvent {
    private final PaymentId paymentId;
    private final String brokerPaymentId;

    public BrokerPaymentIdAssignedEvent(Payment payment) {
        this.paymentId = payment.getId();
        this.brokerPaymentId = payment.getBrokerPaymentId();
    }
}
