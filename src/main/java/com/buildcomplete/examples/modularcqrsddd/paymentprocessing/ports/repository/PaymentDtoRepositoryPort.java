package com.buildcomplete.examples.modularcqrsddd.paymentprocessing.ports.repository;

import java.util.Optional;
import java.util.UUID;

public interface PaymentDtoRepositoryPort {
    Optional<PaymentDto> findById(UUID id);
    Optional<PaymentDto> findByBrokerPaymentId(String brokerPaymentId);
    void save(PaymentDto paymentDto);
}
