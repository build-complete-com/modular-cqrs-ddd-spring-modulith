package com.buildcomplete.examples.modularcqrsddd.orderprocessing.application;

import com.buildcomplete.examples.modularcqrsddd.domainsharedkernel.OrderId;
import com.buildcomplete.examples.modularcqrsddd.orderprocessing.application.domain.LineItem;
import com.buildcomplete.examples.modularcqrsddd.orderprocessing.application.domain.LineItemId;
import com.buildcomplete.examples.modularcqrsddd.orderprocessing.application.domain.Order;
import com.buildcomplete.examples.modularcqrsddd.orderprocessing.application.domain.ProductId;
import com.buildcomplete.examples.modularcqrsddd.orderprocessing.ports.repository.LineItemDto;
import com.buildcomplete.examples.modularcqrsddd.orderprocessing.ports.repository.OrderDto;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
class OrderDtoConverter {

  OrderDto convert(Order order) {
    List<LineItemDto> listItemDtos = order.getLineItems().stream()
        .map(this::convert)
        .toList();
    return OrderDto.builder()
        .id(order.getId().getValue())
        .lineItems(listItemDtos)
        .paymentComplete(order.isPaymentComplete())
        .version(order.getVersion())
        .build();
  }

  Order convert(OrderDto orderDto) {
    List<LineItem> lineItems = orderDto.getLineItems().stream()
        .map(this::convert)
        .toList();
    return Order.reconstitutingBuilder()
        .id(OrderId.of(orderDto.getId()))
        .lineItems(lineItems)
        .paymentComplete(orderDto.isPaymentComplete())
        .version(orderDto.getVersion())
        .build();
  }

  private LineItemDto convert(LineItem lineItem) {
    return LineItemDto.builder()
        .id(lineItem.getId().getValue())
        .productId(lineItem.getProductId().getValue())
        .quantity(lineItem.getQuantity())
        .pricePerUnit(lineItem.getPricePerUnit())
        .build();
  }

  private LineItem convert(LineItemDto lineItemDto) {
    return LineItem.reconstitutingBuilder()
        .id(LineItemId.of(lineItemDto.getId()))
        .productId(ProductId.of(lineItemDto.getProductId()))
        .quantity(lineItemDto.getQuantity())
        .pricePerUnit(lineItemDto.getPricePerUnit())
        .build();
  }
}
