package com.buildcomplete.examples.modularcqrsddd.write.application.paymentprocessing;

import com.buildcomplete.examples.modularcqrsddd.domain.framework.DomainAggregateChange;
import com.buildcomplete.examples.modularcqrsddd.domain.paymentprocessing.Payment;
import com.buildcomplete.examples.modularcqrsddd.domain.paymentprocessing.PaymentFactory;
import com.buildcomplete.examples.modularcqrsddd.domain.paymentprocessing.PaymentId;
import com.buildcomplete.examples.modularcqrsddd.domain.paymentprocessing.PaymentRepository;
import com.buildcomplete.examples.modularcqrsddd.domain.sharedkernel.OrderId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentManagementApplicationService implements PaymentManager {
    private final PaymentFactory paymentFactory;
    private final PaymentRepository paymentRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public PaymentId startPayment(OrderId orderId) {
        DomainAggregateChange<Payment> aggregateChange = paymentFactory.startPayment(orderId);
        paymentRepository.save(aggregateChange.getChangedAggregate());
        aggregateChange.getDomainEvents().forEach(eventPublisher::publishEvent);
        return aggregateChange.getChangedAggregate().getId();
    }

    @Override
    public void completePayment(String brokerPaymentId) {
        Payment payment = paymentRepository.findByBrokerPaymentId(brokerPaymentId).orElseThrow(() -> new IllegalStateException("Payment should exist"));
        DomainAggregateChange<Payment> aggregateChange = payment.complete();
        paymentRepository.save(aggregateChange.getChangedAggregate());
        aggregateChange.getDomainEvents().forEach(eventPublisher::publishEvent);
    }
}
