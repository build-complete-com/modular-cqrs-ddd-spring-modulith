package com.buildcomplete.examples.modularcqrsddd.orderprocessing.ports.repository;

import java.util.List;
import java.util.UUID;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class OrderDto {
  UUID id;
  List<LineItemDto> lineItems;
  boolean paymentComplete;
  Long version;
}
