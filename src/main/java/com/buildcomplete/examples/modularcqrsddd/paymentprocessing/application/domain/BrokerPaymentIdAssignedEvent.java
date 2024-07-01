package com.buildcomplete.examples.modularcqrsddd.paymentprocessing.application.domain;

import com.buildcomplete.examples.modularcqrsddd.domainframework.DomainEvent;
import java.util.UUID;
import lombok.Value;
import org.springframework.data.annotation.PersistenceCreator;

@Value
public class BrokerPaymentIdAssignedEvent extends DomainEvent {
    private final PaymentId paymentId;
    private final String brokerPaymentId;

    public BrokerPaymentIdAssignedEvent(Payment payment) {
        this.paymentId = payment.getId();
        this.brokerPaymentId = payment.getBrokerPaymentId();
    }

    @PersistenceCreator
    BrokerPaymentIdAssignedEvent(UUID id, PaymentId paymentId, String brokerPaymentId) {
        super(id);
        this.paymentId = paymentId;
        this.brokerPaymentId = brokerPaymentId;
    }
}
