package com.buildcomplete.examples.modularcqrsddd.orderprocessing.application;

import com.buildcomplete.examples.modularcqrsddd.hexagoncore.application.domainframework.DomainAggregateChange;
import com.buildcomplete.examples.modularcqrsddd.orderprocessing.application.domain.Order;
import com.buildcomplete.examples.modularcqrsddd.orderprocessing.application.domain.OrderFactory;
import com.buildcomplete.examples.modularcqrsddd.orderprocessing.application.domain.OrderSubmission;
import com.buildcomplete.examples.modularcqrsddd.orderprocessing.ports.repository.OrderDto;
import com.buildcomplete.examples.modularcqrsddd.orderprocessing.ports.repository.OrderDtoRepositoryPort;
import com.buildcomplete.examples.modularcqrsddd.orderprocessing.ports.service.OrderManagerPort;
import com.buildcomplete.examples.modularcqrsddd.orderprocessing.ports.service.OrderSubmissionDto;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class OrderManagementApplicationService implements OrderManagerPort {
  private final OrderDtoConverter orderDtoConverter;
  private final OrderSubmissionConverter orderSubmissionConverter;
  private final OrderDtoRepositoryPort orderRepository;
  private final OrderFactory orderFactory;
  private final ApplicationEventPublisher eventPublisher;

  @Override
  @Transactional("transactionManager")
  public UUID submitOrder(OrderSubmissionDto orderSubmission) {
    DomainAggregateChange<Order> aggregateChange = createOrder(orderSubmission);
    saveOrder(aggregateChange.getChangedAggregate());
    aggregateChange.getDomainEvents().forEach(eventPublisher::publishEvent);
    return aggregateChange.getChangedAggregate().getId().getValue();
  }

  private DomainAggregateChange<Order> createOrder(OrderSubmissionDto orderSubmissionDto) {
    OrderSubmission orderSubmission = orderSubmissionConverter.convert(orderSubmissionDto);
    DomainAggregateChange<Order> aggregateChange = orderFactory.createOrder(orderSubmission);
    return aggregateChange;
  }

  private void saveOrder(Order order) {
    OrderDto orderDto = orderDtoConverter.convert(order);
    orderRepository.save(orderDto);
  }
}
