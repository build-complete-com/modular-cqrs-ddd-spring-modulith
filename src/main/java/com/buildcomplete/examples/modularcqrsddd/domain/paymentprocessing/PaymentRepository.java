package com.buildcomplete.examples.modularcqrsddd.domain.paymentprocessing;

import com.buildcomplete.examples.modularcqrsddd.domain.framework.DomainRepository;

import java.util.Optional;

public interface PaymentRepository extends DomainRepository<Payment, PaymentId> {
    Optional<Payment> findByBrokerPaymentId(String brokerPaymentId);
}
