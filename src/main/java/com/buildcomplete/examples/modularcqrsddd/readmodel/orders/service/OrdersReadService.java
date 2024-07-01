package com.buildcomplete.examples.modularcqrsddd.readmodel.orders.service;

import com.buildcomplete.examples.modularcqrsddd.orderprocessing.ports.events.OrderPayedPortEvent;
import com.buildcomplete.examples.modularcqrsddd.orderprocessing.ports.events.OrderSubmittedPortEvent;
import com.buildcomplete.examples.modularcqrsddd.readmodel.orders.repository.OrderEntity;
import com.buildcomplete.examples.modularcqrsddd.readmodel.orders.repository.OrderReadRepository;
import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrdersReadService {
  private final OrderReadRepository repository;

  public List<OrderEntity> getOrders() {
    return repository.findAll();
  }

  @Transactional(propagation = Propagation.REQUIRES_NEW, transactionManager = "jpaTransactionManager")
  @ApplicationModuleListener
  void handleEvent(OrderSubmittedPortEvent event) {
    OrderEntity order = new OrderEntity();
    order.setId(event.getOrderId());
    order.setTotalCost(getTotalCost(event));
    order.setPaymentComplete(false);
    repository.save(order);
  }

  @Transactional(propagation = Propagation.REQUIRES_NEW, transactionManager = "jpaTransactionManager")
  @ApplicationModuleListener
  void handleEvent(OrderPayedPortEvent event) {
    OrderEntity order = repository.findById(event.getOrderId())
        .orElseThrow(() -> new IllegalStateException("Order should exist"));
    order.setPaymentComplete(true);
    repository.save(order);
  }

  private BigDecimal getTotalCost(OrderSubmittedPortEvent event) {
    return event.getLineItems().stream()
        .map(lineItem -> lineItem.getPricePerUnit().multiply(BigDecimal.valueOf(lineItem.getQuantity())))
        .reduce(BigDecimal.ZERO, (accumulator, lineItemCost) -> accumulator.add(lineItemCost));
  }
}
