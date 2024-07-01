package com.buildcomplete.examples.modularcqrsddd.orderprocessing.application.domain;

import java.util.List;
import lombok.Value;

@Value
public class OrderSubmission {
  private List<Entry> entries;

  @Value
  public static class Entry {
    ProductId productId;
    int quantity;
  }
}
