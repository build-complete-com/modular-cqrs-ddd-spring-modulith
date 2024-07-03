package com.buildcomplete.examples.modularcqrsddd.orderprocessing.application.domain;

import com.buildcomplete.examples.modularcqrsddd.hexagoncore.application.domainframework.DomainEvent;
import com.buildcomplete.examples.modularcqrsddd.hexagoncore.application.domainsharedkernel.OrderId;
import java.util.List;
import java.util.UUID;
import lombok.Value;
import org.springframework.data.annotation.PersistenceCreator;

@Value
public class OrderSubmittedEvent extends DomainEvent {
    private final OrderId orderId;
    private final List<LineItem> lineItems;

    public OrderSubmittedEvent(Order order) {
        this.orderId = order.getId();
        this.lineItems = order.getLineItems();
    }

    @PersistenceCreator
    OrderSubmittedEvent(UUID id, OrderId orderId, List<LineItem> lineItems) {
        super(id);
        this.orderId = orderId;
        this.lineItems = lineItems;
    }
}
