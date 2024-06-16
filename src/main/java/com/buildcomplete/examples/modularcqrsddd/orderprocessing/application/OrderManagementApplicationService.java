package com.buildcomplete.examples.modularcqrsddd.orderprocessing.application;

import com.buildcomplete.examples.modularcqrsddd.domainframework.DomainAggregateChange;
import com.buildcomplete.examples.modularcqrsddd.domainsharedkernel.OrderId;
import com.buildcomplete.examples.modularcqrsddd.orderprocessing.domain.Order;
import com.buildcomplete.examples.modularcqrsddd.orderprocessing.domain.OrderFactory;
import com.buildcomplete.examples.modularcqrsddd.orderprocessing.domain.OrderRepository;
import com.buildcomplete.examples.modularcqrsddd.orderprocessing.domain.ProductId;
import java.util.SortedMap;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class OrderManagementApplicationService implements OrderManager {
    private final OrderRepository orderRepository;
    private final OrderFactory orderFactory;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    @Transactional("transactionManager")
    public OrderId submitOrder(SortedMap<ProductId, Integer> productQuantitiesMap) {
        DomainAggregateChange<Order> aggregateChange = orderFactory.createOrder(productQuantitiesMap);
        orderRepository.save(aggregateChange.getChangedAggregate());
        aggregateChange.getDomainEvents().forEach(eventPublisher::publishEvent);
        return aggregateChange.getChangedAggregate().getId();
    }
}
