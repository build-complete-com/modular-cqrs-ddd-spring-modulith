package com.buildcomplete.examples.modularcqrsddd.orderprocessing.ports.service;

import java.util.List;
import java.util.UUID;
import lombok.Value;

@Value
public class OrderSubmissionDto {
  private List<Entry> entries;

  @Value
  public static class Entry {
    UUID productId;
    int quantity;
  }
}
