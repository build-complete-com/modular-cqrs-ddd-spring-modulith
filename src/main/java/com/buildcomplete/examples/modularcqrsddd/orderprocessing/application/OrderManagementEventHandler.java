package com.buildcomplete.examples.modularcqrsddd.orderprocessing.application;

import com.buildcomplete.examples.modularcqrsddd.hexagoncore.application.domainframework.DomainAggregateChange;
import com.buildcomplete.examples.modularcqrsddd.orderprocessing.application.domain.Order;
import com.buildcomplete.examples.modularcqrsddd.orderprocessing.ports.repository.OrderDto;
import com.buildcomplete.examples.modularcqrsddd.orderprocessing.ports.repository.OrderDtoRepositoryPort;
import com.buildcomplete.examples.modularcqrsddd.paymentprocessing.ports.events.PaymentCompletedPortEvent;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class OrderManagementEventHandler {
    private final OrderDtoConverter orderDtoConverter;
    private final OrderDtoRepositoryPort orderRepository;
    private final ApplicationEventPublisher eventPublisher;

    @ApplicationModuleListener
    void handleEvent(PaymentCompletedPortEvent event) {
        Order order = getOrderById(event.getOrderId());
        DomainAggregateChange<Order> aggregateChange = order.completePayment();
        saveOrder(aggregateChange.getChangedAggregate());
        aggregateChange.getDomainEvents().forEach(eventPublisher::publishEvent);
    }

    private void saveOrder(Order order) {
        OrderDto changedOrderDto = orderDtoConverter.convert(order);
        orderRepository.save(changedOrderDto);
    }

    private Order getOrderById(UUID orderId) {
        OrderDto orderDto = orderRepository.findById(orderId)
            .orElseThrow(() -> new IllegalStateException("Order should exist"));
        Order order = orderDtoConverter.convert(orderDto);
        return order;
    }
}
