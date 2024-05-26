package com.buildcomplete.examples.modularcqrsddd.paymentprocessingapplication;

import com.buildcomplete.examples.modularcqrsddd.domainframework.DomainAggregateChange;
import com.buildcomplete.examples.modularcqrsddd.paymentprocessingdomain.Payment;
import com.buildcomplete.examples.modularcqrsddd.paymentprocessingdomain.PaymentBroker;
import com.buildcomplete.examples.modularcqrsddd.paymentprocessingdomain.PaymentRepository;
import com.buildcomplete.examples.modularcqrsddd.paymentprocessingdomain.PaymentStartedEvent;
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
