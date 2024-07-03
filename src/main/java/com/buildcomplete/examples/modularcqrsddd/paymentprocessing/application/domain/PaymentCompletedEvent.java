package com.buildcomplete.examples.modularcqrsddd.paymentprocessing.application.domain;

import com.buildcomplete.examples.modularcqrsddd.hexagoncore.application.domainframework.DomainEvent;
import com.buildcomplete.examples.modularcqrsddd.hexagoncore.application.domainsharedkernel.OrderId;
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
