package com.buildcomplete.examples.modularcqrsddd.orderprocessing.ports.events;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import lombok.Value;

@Value
public class OrderSubmittedPortEvent {
  UUID orderId;
  List<LineItemDto> lineItems;

  @Value
  public static class LineItemDto {
    UUID productId;
    int quantity;
    BigDecimal pricePerUnit;
  }
}
