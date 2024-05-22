package com.buildcomplete.examples.modularcqrsddd.read.orders.service;

import com.buildcomplete.examples.modularcqrsddd.domain.orderprocessing.OrderSubmittedEvent;
import com.buildcomplete.examples.modularcqrsddd.domain.sharedkernel.OrderPayedEvent;
import com.buildcomplete.examples.modularcqrsddd.read.orders.repository.OrderEntity;
import com.buildcomplete.examples.modularcqrsddd.read.orders.repository.OrderReadRepository;
import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrdersReadService {
  private final OrderReadRepository repository;

  public List<OrderEntity> getOrders() {
    return repository.findAll();
  }

  @Async
  @EventListener
  void handleEvent(OrderSubmittedEvent event) {
    OrderEntity order = new OrderEntity();
    order.setId(event.getOrderId().getValue());
    order.setTotalCost(getTotalCost(event));
    order.setPaymentComplete(false);
    repository.save(order);
  }

  @Async
  @EventListener
  void handleEvent(OrderPayedEvent event) {
    OrderEntity order = repository.findById(event.getOrderId().getValue())
        .orElseThrow(() -> new IllegalStateException("Order should exist"));
    order.setPaymentComplete(true);
    repository.save(order);
  }

  private BigDecimal getTotalCost(OrderSubmittedEvent event) {
    return event.getLineItems().stream()
        .map(lineItem -> lineItem.getPricePerUnit().multiply(BigDecimal.valueOf(lineItem.getQuantity())))
        .reduce(BigDecimal.ZERO, (accumulator, lineItemCost) -> accumulator.add(lineItemCost));
  }
}
