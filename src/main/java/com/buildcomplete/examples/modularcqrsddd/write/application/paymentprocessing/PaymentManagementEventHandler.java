package com.buildcomplete.examples.modularcqrsddd.write.application.paymentprocessing;

import com.buildcomplete.examples.modularcqrsddd.domain.framework.DomainAggregateChange;
import com.buildcomplete.examples.modularcqrsddd.domain.paymentprocessing.Payment;
import com.buildcomplete.examples.modularcqrsddd.domain.paymentprocessing.PaymentBroker;
import com.buildcomplete.examples.modularcqrsddd.domain.paymentprocessing.PaymentRepository;
import com.buildcomplete.examples.modularcqrsddd.domain.paymentprocessing.PaymentStartedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class PaymentManagementEventHandler {
    private final PaymentRepository paymentRepository;
    private final PaymentBroker paymentBroker;
    private final ApplicationEventPublisher eventPublisher;

    @Async
    @EventListener
    void handleEvent(PaymentStartedEvent event) {
        Payment payment = paymentRepository.findById(event.getPaymentId()).orElseThrow(() -> new IllegalStateException("Payment should exist"));
        String brokerPaymentId = paymentBroker.getBrokerPaymentId();
        DomainAggregateChange<Payment> aggregateChange = payment.assignBrokerPaymentId(brokerPaymentId);
        paymentRepository.save(aggregateChange.getChangedAggregate());
        aggregateChange.getDomainEvents().forEach(eventPublisher::publishEvent);
    }
}
