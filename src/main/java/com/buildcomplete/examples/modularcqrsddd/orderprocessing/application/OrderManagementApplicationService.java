package com.buildcomplete.examples.modularcqrsddd.orderprocessing.application;

import com.buildcomplete.examples.modularcqrsddd.domainframework.DomainAggregateChange;
import com.buildcomplete.examples.modularcqrsddd.orderprocessing.domain.Order;
import com.buildcomplete.examples.modularcqrsddd.orderprocessing.domain.OrderFactory;
import com.buildcomplete.examples.modularcqrsddd.orderprocessing.domain.OrderRepository;
import com.buildcomplete.examples.modularcqrsddd.orderprocessing.domain.ProductId;
import com.buildcomplete.examples.modularcqrsddd.domainsharedkernel.OrderId;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Map;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class OrderManagementApplicationService implements OrderManager {
    private final OrderRepository orderRepository;
    private final OrderFactory orderFactory;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    @Transactional("transactionManager")
    public OrderId submitOrder(Map<ProductId, Integer> productQuantitiesMap) {
        DomainAggregateChange<Order> aggregateChange = orderFactory.createOrder(productQuantitiesMap);
        orderRepository.save(aggregateChange.getChangedAggregate());
        aggregateChange.getDomainEvents().forEach(eventPublisher::publishEvent);
        return aggregateChange.getChangedAggregate().getId();
    }
}
