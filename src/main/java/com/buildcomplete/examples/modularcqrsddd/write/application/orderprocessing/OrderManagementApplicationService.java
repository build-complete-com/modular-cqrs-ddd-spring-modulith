package com.buildcomplete.examples.modularcqrsddd.write.application.orderprocessing;

import com.buildcomplete.examples.modularcqrsddd.domain.framework.DomainAggregateChange;
import com.buildcomplete.examples.modularcqrsddd.domain.orderprocessing.Order;
import com.buildcomplete.examples.modularcqrsddd.domain.orderprocessing.OrderFactory;
import com.buildcomplete.examples.modularcqrsddd.domain.orderprocessing.OrderRepository;
import com.buildcomplete.examples.modularcqrsddd.domain.orderprocessing.ProductId;
import com.buildcomplete.examples.modularcqrsddd.domain.sharedkernel.OrderId;
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
