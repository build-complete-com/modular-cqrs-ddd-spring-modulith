package com.buildcomplete.examples.modularcqrsddd.domainsharedkernel;

import com.buildcomplete.examples.modularcqrsddd.domainframework.DomainEvent;
import java.util.UUID;
import lombok.Value;
import org.springframework.data.annotation.PersistenceCreator;

@Value
public class OrderPayedEvent extends DomainEvent {
    private final OrderId orderId;

    public OrderPayedEvent(OrderId orderId) {
        this.orderId = orderId;
    }

    @PersistenceCreator
    OrderPayedEvent(UUID id, OrderId orderId) {
        super(id);
        this.orderId = orderId;
    }
}
