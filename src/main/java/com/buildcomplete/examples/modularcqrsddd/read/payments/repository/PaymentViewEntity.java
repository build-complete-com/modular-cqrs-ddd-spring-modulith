package com.buildcomplete.examples.modularcqrsddd.read.payments.repository;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
public class PaymentViewEntity {
    @Id
    private UUID paymentId;
    private PaymentState paymentState;
}
