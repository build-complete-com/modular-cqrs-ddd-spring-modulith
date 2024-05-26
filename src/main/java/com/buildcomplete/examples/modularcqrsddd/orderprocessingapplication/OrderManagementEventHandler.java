package com.buildcomplete.examples.modularcqrsddd.orderprocessingapplication;

import com.buildcomplete.examples.modularcqrsddd.domainframework.DomainAggregateChange;
import com.buildcomplete.examples.modularcqrsddd.domainsharedkernel.PaymentCompletedEvent;
import com.buildcomplete.examples.modularcqrsddd.orderprocessingdomain.Order;
import com.buildcomplete.examples.modularcqrsddd.orderprocessingdomain.OrderFactory;
import com.buildcomplete.examples.modularcqrsddd.orderprocessingdomain.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class OrderManagementEventHandler {
    private final OrderRepository orderRepository;
    private final OrderFactory orderFactory;
    private final ApplicationEventPublisher eventPublisher;

    @ApplicationModuleListener
    void handleEvent(PaymentCompletedEvent event) {
        Order order = orderRepository.findById(event.getOrderId()).orElseThrow(() -> new IllegalStateException("Order should exist"));
        DomainAggregateChange<Order> aggregateChange = order.completePayment();
        orderRepository.save(aggregateChange.getChangedAggregate());
        aggregateChange.getDomainEvents().forEach(eventPublisher::publishEvent);
    }
}
