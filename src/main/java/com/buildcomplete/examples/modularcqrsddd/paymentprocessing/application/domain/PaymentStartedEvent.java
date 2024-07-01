package com.buildcomplete.examples.modularcqrsddd.paymentprocessing.application.domain;

import com.buildcomplete.examples.modularcqrsddd.domainframework.DomainEvent;
import com.buildcomplete.examples.modularcqrsddd.domainsharedkernel.PaymentId;
import java.util.UUID;
import lombok.Value;
import org.springframework.data.annotation.PersistenceCreator;

@Value
public class PaymentStartedEvent extends DomainEvent {
    private final PaymentId paymentId;

    public PaymentStartedEvent(Payment payment) {
        this.paymentId = payment.getId();
    }

    @PersistenceCreator
    PaymentStartedEvent(UUID id, PaymentId paymentId) {
        super(id);
        this.paymentId = paymentId;
    }
}
