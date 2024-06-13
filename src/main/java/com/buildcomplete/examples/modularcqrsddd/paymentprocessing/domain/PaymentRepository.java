package com.buildcomplete.examples.modularcqrsddd.paymentprocessing.domain;

import com.buildcomplete.examples.modularcqrsddd.domainframework.DomainRepository;

import com.buildcomplete.examples.modularcqrsddd.domainsharedkernel.PaymentId;
import java.util.Optional;

public interface PaymentRepository extends DomainRepository<Payment, PaymentId> {
    Optional<Payment> findByBrokerPaymentId(String brokerPaymentId);
}
