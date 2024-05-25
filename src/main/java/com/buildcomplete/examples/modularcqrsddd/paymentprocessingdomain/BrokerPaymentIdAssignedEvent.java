package com.buildcomplete.examples.modularcqrsddd.paymentprocessingdomain;

import com.buildcomplete.examples.modularcqrsddd.domainframework.DomainEvent;
import com.buildcomplete.examples.modularcqrsddd.domainsharedkernel.PaymentId;
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
