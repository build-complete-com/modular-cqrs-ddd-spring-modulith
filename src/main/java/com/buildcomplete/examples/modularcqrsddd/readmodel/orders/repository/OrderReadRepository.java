package com.buildcomplete.examples.modularcqrsddd.readmodel.orders.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderReadRepository extends JpaRepository<OrderEntity, UUID> {
}
