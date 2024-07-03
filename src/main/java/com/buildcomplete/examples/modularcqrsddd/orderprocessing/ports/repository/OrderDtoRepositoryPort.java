package com.buildcomplete.examples.modularcqrsddd.orderprocessing.ports.repository;

import java.util.Optional;
import java.util.UUID;

public interface OrderDtoRepositoryPort {
  Optional<OrderDto> findById(UUID id);

  void save(OrderDto order);
}
