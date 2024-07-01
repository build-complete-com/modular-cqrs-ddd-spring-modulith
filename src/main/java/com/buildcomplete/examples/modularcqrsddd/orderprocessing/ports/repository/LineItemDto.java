package com.buildcomplete.examples.modularcqrsddd.orderprocessing.ports.repository;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class LineItemDto {
  UUID id;
  UUID productId;
  int quantity;
  BigDecimal pricePerUnit;
}
