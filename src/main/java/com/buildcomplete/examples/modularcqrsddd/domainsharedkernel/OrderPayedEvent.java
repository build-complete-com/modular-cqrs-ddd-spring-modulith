package com.buildcomplete.examples.modularcqrsddd.domainsharedkernel;

import com.buildcomplete.examples.modularcqrsddd.domainframework.DomainEvent;
import lombok.Value;

@Value
public class OrderPayedEvent extends DomainEvent {
    private final OrderId orderId;

    public OrderPayedEvent(OrderId orderId) {
        this.orderId = orderId;
    }
}
