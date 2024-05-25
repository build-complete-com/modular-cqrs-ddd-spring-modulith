package com.buildcomplete.examples.modularcqrsddd.readmodel.orders.repository;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Entity
public class OrderEntity {
    @Id
    private UUID id;
    private BigDecimal totalCost;
    private boolean paymentComplete;
}
