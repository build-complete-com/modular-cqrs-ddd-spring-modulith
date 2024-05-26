package com.buildcomplete.examples.modularcqrsddd.domainsharedkernel;

import com.buildcomplete.examples.modularcqrsddd.domainframework.DomainEvent;
import java.util.UUID;
import lombok.Value;
import org.springframework.data.annotation.PersistenceCreator;

@Value
public class PaymentCompletedEvent extends DomainEvent {
    private final PaymentId paymentId;
    private final OrderId orderId;

    public PaymentCompletedEvent(PaymentId paymentId, OrderId orderId) {
        this.paymentId = paymentId;
        this.orderId = orderId;
    }

    @PersistenceCreator
    PaymentCompletedEvent(UUID id, PaymentId paymentId, OrderId orderId) {
        super(id);
        this.paymentId = paymentId;
        this.orderId = orderId;
    }
}
