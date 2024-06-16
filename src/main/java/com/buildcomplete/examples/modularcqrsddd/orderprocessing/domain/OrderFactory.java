package com.buildcomplete.examples.modularcqrsddd.orderprocessing.domain;

import com.buildcomplete.examples.modularcqrsddd.domainframework.DomainAggregateChange;
import java.util.LinkedHashMap;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class OrderFactory {
    public DomainAggregateChange<Order> createOrder(LinkedHashMap<ProductId, Integer> productQuantitiesMap) {
        Order order = Order.constitutingBuilder()
                .lineItems(toLineItems(productQuantitiesMap))
                .build();
        OrderSubmittedEvent orderSubmittedEvent = new OrderSubmittedEvent(order);
        return DomainAggregateChange.<Order>builder()
                .changedAggregate(order)
                .domainEvents(List.of(orderSubmittedEvent))
                .build();
    }

    private List<LineItem> toLineItems(LinkedHashMap<ProductId, Integer> productQuantitiesMap) {
        return productQuantitiesMap.entrySet().stream()
                .map(entry -> LineItem.constitutingBuilder()
                        .productId(entry.getKey())
                        .quantity(entry.getValue())
                        .build())
                .toList();
    }
}
