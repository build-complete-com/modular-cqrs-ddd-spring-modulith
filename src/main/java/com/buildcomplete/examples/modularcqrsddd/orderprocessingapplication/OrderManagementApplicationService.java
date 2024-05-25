package com.buildcomplete.examples.modularcqrsddd.orderprocessingapplication;

import com.buildcomplete.examples.modularcqrsddd.domainframework.DomainAggregateChange;
import com.buildcomplete.examples.modularcqrsddd.orderprocessingdomain.Order;
import com.buildcomplete.examples.modularcqrsddd.orderprocessingdomain.OrderFactory;
import com.buildcomplete.examples.modularcqrsddd.orderprocessingdomain.OrderRepository;
import com.buildcomplete.examples.modularcqrsddd.orderprocessingdomain.ProductId;
import com.buildcomplete.examples.modularcqrsddd.domainsharedkernel.OrderId;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
class OrderManagementApplicationService implements OrderManager {
    private final OrderRepository orderRepository;
    private final OrderFactory orderFactory;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public OrderId submitOrder(Map<ProductId, Integer> productQuantitiesMap) {
        DomainAggregateChange<Order> aggregateChange = orderFactory.createOrder(productQuantitiesMap);
        orderRepository.save(aggregateChange.getChangedAggregate());
        aggregateChange.getDomainEvents().forEach(eventPublisher::publishEvent);
        return aggregateChange.getChangedAggregate().getId();
    }
}
