package com.buildcomplete.examples.modularcqrsddd.orderprocessing.application;

import com.buildcomplete.examples.modularcqrsddd.orderprocessing.application.domain.LineItem;
import com.buildcomplete.examples.modularcqrsddd.orderprocessing.application.domain.OrderSubmittedEvent;
import com.buildcomplete.examples.modularcqrsddd.orderprocessing.ports.events.OrderSubmittedPortEvent;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
class OrderSubmittedPortEventConverter {

  OrderSubmittedPortEvent convert(OrderSubmittedEvent domainEvent) {
    List<OrderSubmittedPortEvent.LineItemDto> lineItemDtos = domainEvent.getLineItems().stream().map(this::convert).toList();
    return new OrderSubmittedPortEvent(domainEvent.getOrderId().getValue(), lineItemDtos);
  }

  private OrderSubmittedPortEvent.LineItemDto convert(LineItem lineItem) {
    return new OrderSubmittedPortEvent.LineItemDto(lineItem.getProductId().getValue(), lineItem.getQuantity(), lineItem.getPricePerUnit());
  }
}
