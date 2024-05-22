package com.buildcomplete.examples.modularcqrsddd.domain.orderprocessing;

import com.buildcomplete.examples.modularcqrsddd.domain.framework.DomainAggregateChange;
import com.buildcomplete.examples.modularcqrsddd.domain.framework.DomainAggregateRoot;
import com.buildcomplete.examples.modularcqrsddd.domain.sharedkernel.OrderId;
import com.buildcomplete.examples.modularcqrsddd.domain.sharedkernel.OrderPayedEvent;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
public class Order extends DomainAggregateRoot<OrderId> {
    List<LineItem> lineItems;
    boolean paymentComplete;

    @Builder(builderMethodName = "reconstitutingBuilder", builderClassName = "ReconstitutingBuilder", toBuilder = true)
    public Order(OrderId id, Long version, List<LineItem> lineItems, boolean paymentComplete) {
        super(id, version);
        this.lineItems = lineItems;
        this.paymentComplete = paymentComplete;
    }

    @Builder(builderMethodName = "constitutingBuilder", builderClassName = "ConstitutingBuilder")
    public Order(List<LineItem> lineItems) {
        super(OrderId.randomOrderId(), null);
        this.lineItems = lineItems;
        this.paymentComplete = false;
    }

    public DomainAggregateChange<Order> completePayment() {
        Order changedOrder = this.toBuilder()
                .paymentComplete(true)
                .build();
        OrderPayedEvent orderPayedEvent = new OrderPayedEvent(changedOrder);
        return DomainAggregateChange.<Order>builder()
                .changedAggregate(changedOrder)
                .domainEvents(List.of(orderPayedEvent))
                .build();
    }
}
