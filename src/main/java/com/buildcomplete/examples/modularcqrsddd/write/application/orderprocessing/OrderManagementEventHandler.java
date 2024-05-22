package com.buildcomplete.examples.modularcqrsddd.write.application.orderprocessing;

import com.buildcomplete.examples.modularcqrsddd.domain.framework.DomainAggregateChange;
import com.buildcomplete.examples.modularcqrsddd.domain.orderprocessing.Order;
import com.buildcomplete.examples.modularcqrsddd.domain.orderprocessing.OrderFactory;
import com.buildcomplete.examples.modularcqrsddd.domain.orderprocessing.OrderRepository;
import com.buildcomplete.examples.modularcqrsddd.domain.sharedkernel.PaymentCompletedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class OrderManagementEventHandler {
    private final OrderRepository orderRepository;
    private final OrderFactory orderFactory;
    private final ApplicationEventPublisher eventPublisher;

    @Async
    @EventListener
    void handleEvent(PaymentCompletedEvent event) {
        Order order = orderRepository.findById(event.getOrderId()).orElseThrow(() -> new IllegalStateException("Order should exist"));
        DomainAggregateChange<Order> aggregateChange = order.completePayment();
        orderRepository.save(aggregateChange.getChangedAggregate());
        aggregateChange.getDomainEvents().forEach(eventPublisher::publishEvent);
    }
}
