package com.buildcomplete.examples.modularcqrsddd.domain.orderprocessing;

import com.buildcomplete.examples.modularcqrsddd.domain.framework.DomainEvent;
import com.buildcomplete.examples.modularcqrsddd.domain.sharedkernel.OrderId;
import lombok.Value;

import java.util.List;

@Value
public class OrderSubmittedEvent extends DomainEvent {
    private final OrderId orderId;
    private final List<LineItem> lineItems;

    public OrderSubmittedEvent(Order order) {
        this.orderId = order.getId();
        this.lineItems = order.getLineItems();
    }
}
