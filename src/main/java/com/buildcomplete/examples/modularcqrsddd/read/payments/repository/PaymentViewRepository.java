package com.buildcomplete.examples.modularcqrsddd.read.payments.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PaymentViewRepository extends JpaRepository<PaymentViewEntity, UUID> {
}
