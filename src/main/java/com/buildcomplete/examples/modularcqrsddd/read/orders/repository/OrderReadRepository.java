package com.buildcomplete.examples.modularcqrsddd.read.orders.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderReadRepository extends JpaRepository<OrderEntity, UUID> {
}
