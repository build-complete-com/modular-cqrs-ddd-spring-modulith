package com.buildcomplete.examples.modularcqrsddd.orderprocessing.application.domain;

import com.buildcomplete.examples.modularcqrsddd.hexagoncore.application.domainframework.DomainAggregateChange;
import com.buildcomplete.examples.modularcqrsddd.hexagoncore.application.domainframework.DomainAggregateRoot;
import com.buildcomplete.examples.modularcqrsddd.hexagoncore.application.domainsharedkernel.OrderId;
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
        OrderPayedEvent orderPayedEvent = new OrderPayedEvent(changedOrder.getId());
        return DomainAggregateChange.<Order>builder()
                .changedAggregate(changedOrder)
                .domainEvents(List.of(orderPayedEvent))
                .build();
    }
}
