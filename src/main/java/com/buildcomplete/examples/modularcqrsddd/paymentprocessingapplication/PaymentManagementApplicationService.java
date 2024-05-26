package com.buildcomplete.examples.modularcqrsddd.paymentprocessingapplication;

import com.buildcomplete.examples.modularcqrsddd.domainframework.DomainAggregateChange;
import com.buildcomplete.examples.modularcqrsddd.paymentprocessingdomain.Payment;
import com.buildcomplete.examples.modularcqrsddd.paymentprocessingdomain.PaymentFactory;
import com.buildcomplete.examples.modularcqrsddd.domainsharedkernel.PaymentId;
import com.buildcomplete.examples.modularcqrsddd.paymentprocessingdomain.PaymentRepository;
import com.buildcomplete.examples.modularcqrsddd.domainsharedkernel.OrderId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional("transactionManager")
@RequiredArgsConstructor
class PaymentManagementApplicationService implements PaymentManager {
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
