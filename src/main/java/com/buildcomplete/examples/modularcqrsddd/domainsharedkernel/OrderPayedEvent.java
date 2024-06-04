package com.buildcomplete.examples.modularcqrsddd.domainsharedkernel;

import com.buildcomplete.examples.modularcqrsddd.domainframework.DomainEvent;
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
