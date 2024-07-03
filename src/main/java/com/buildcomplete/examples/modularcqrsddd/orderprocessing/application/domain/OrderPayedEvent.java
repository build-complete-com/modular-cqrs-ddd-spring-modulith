package com.buildcomplete.examples.modularcqrsddd.orderprocessing.application.domain;

import com.buildcomplete.examples.modularcqrsddd.hexagoncore.application.domainframework.DomainEvent;
import com.buildcomplete.examples.modularcqrsddd.hexagoncore.application.domainsharedkernel.OrderId;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;
import lombok.Value;
import org.springframework.data.annotation.PersistenceCreator;

@Value
public class OrderPayedEvent extends DomainEvent {
    private final OrderId orderId;

    public OrderPayedEvent(OrderId orderId) {
        this.orderId = orderId;
    }

    @JsonCreator
    @PersistenceCreator
    OrderPayedEvent(@JsonProperty("id") UUID id,
                    @JsonProperty("orderId") OrderId orderId) {
        super(id);
        this.orderId = orderId;
    }
}
