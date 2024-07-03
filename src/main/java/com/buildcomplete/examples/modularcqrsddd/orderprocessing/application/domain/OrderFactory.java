package com.buildcomplete.examples.modularcqrsddd.orderprocessing.application.domain;

import com.buildcomplete.examples.modularcqrsddd.hexagoncore.application.domainframework.DomainAggregateChange;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class OrderFactory {
  public DomainAggregateChange<Order> createOrder(OrderSubmission orderSubmission) {
    Order order = Order.constitutingBuilder()
        .lineItems(toLineItems(orderSubmission))
        .build();
    OrderSubmittedEvent orderSubmittedEvent = new OrderSubmittedEvent(order);
    return DomainAggregateChange.<Order>builder()
        .changedAggregate(order)
        .domainEvents(List.of(orderSubmittedEvent))
        .build();
  }

  private List<LineItem> toLineItems(OrderSubmission orderSubmission) {
    return orderSubmission.getEntries().stream()
        .map(entry -> LineItem.constitutingBuilder()
            .productId(entry.getProductId())
            .quantity(entry.getQuantity())
            .build())
        .toList();
  }
}
