package com.buildcomplete.examples.modularcqrsddd.paymentprocessing.application;

import com.buildcomplete.examples.modularcqrsddd.domainframework.DomainAggregateChange;
import com.buildcomplete.examples.modularcqrsddd.paymentprocessing.domain.Payment;
import com.buildcomplete.examples.modularcqrsddd.paymentprocessing.domain.PaymentBroker;
import com.buildcomplete.examples.modularcqrsddd.paymentprocessing.domain.PaymentRepository;
import com.buildcomplete.examples.modularcqrsddd.paymentprocessing.domain.PaymentStartedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class PaymentManagementEventHandler {
    private final PaymentRepository paymentRepository;
    private final PaymentBroker paymentBroker;
    private final ApplicationEventPublisher eventPublisher;

    @ApplicationModuleListener
    void handleEvent(PaymentStartedEvent event) {
        Payment payment = paymentRepository.findById(event.getPaymentId()).orElseThrow(() -> new IllegalStateException("Payment should exist"));
        String brokerPaymentId = paymentBroker.getBrokerPaymentId();
        DomainAggregateChange<Payment> aggregateChange = payment.assignBrokerPaymentId(brokerPaymentId);
        paymentRepository.save(aggregateChange.getChangedAggregate());
        aggregateChange.getDomainEvents().forEach(eventPublisher::publishEvent);
    }
}
