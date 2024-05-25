package com.buildcomplete.examples.modularcqrsddd.readmodel.payments.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PaymentViewRepository extends JpaRepository<PaymentViewEntity, UUID> {
}
