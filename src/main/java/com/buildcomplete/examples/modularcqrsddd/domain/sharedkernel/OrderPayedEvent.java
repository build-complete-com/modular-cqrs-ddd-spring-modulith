package com.buildcomplete.examples.modularcqrsddd.domain.sharedkernel;

import com.buildcomplete.examples.modularcqrsddd.domain.framework.DomainEvent;
import com.buildcomplete.examples.modularcqrsddd.domain.orderprocessing.Order;
import lombok.Value;

@Value
public class OrderPayedEvent extends DomainEvent {
    private final OrderId orderId;

    public OrderPayedEvent(Order order) {
        this.orderId = order.getId();
    }
}
