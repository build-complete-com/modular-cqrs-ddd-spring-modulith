package com.buildcomplete.examples.modularcqrsddd.domain.orderprocessing;

import com.buildcomplete.examples.modularcqrsddd.domain.framework.DomainAggregateChange;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class OrderFactory {
    public DomainAggregateChange<Order> createOrder(Map<ProductId, Integer> productQuantitiesMap) {
        Order order = Order.constitutingBuilder()
                .lineItems(toLineItems(productQuantitiesMap))
                .build();
        OrderSubmittedEvent orderSubmittedEvent = new OrderSubmittedEvent(order);
        return DomainAggregateChange.<Order>builder()
                .changedAggregate(order)
                .domainEvents(List.of(orderSubmittedEvent))
                .build();
    }

    private List<LineItem> toLineItems(Map<ProductId, Integer> productQuantitiesMap) {
        return productQuantitiesMap.entrySet().stream()
                .map(entry -> LineItem.constitutingBuilder()
                        .productId(entry.getKey())
                        .quantity(entry.getValue())
                        .build())
                .toList();
    }
}
