package com.buildcomplete.examples.modularcqrsddd.domainsharedkernel;

import com.buildcomplete.examples.modularcqrsddd.domainframework.DomainEvent;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonCreator
    @PersistenceCreator
    PaymentCompletedEvent(
        @JsonProperty("id") UUID id,
        @JsonProperty("paymentId") PaymentId paymentId,
        @JsonProperty("orderId") OrderId orderId) {
        super(id);
        this.paymentId = paymentId;
        this.orderId = orderId;
    }
}
