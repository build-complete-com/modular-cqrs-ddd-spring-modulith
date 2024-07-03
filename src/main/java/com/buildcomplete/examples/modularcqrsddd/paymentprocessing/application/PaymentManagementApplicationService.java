package com.buildcomplete.examples.modularcqrsddd.paymentprocessing.application;

import com.buildcomplete.examples.modularcqrsddd.hexagoncore.application.domainframework.DomainAggregateChange;
import com.buildcomplete.examples.modularcqrsddd.paymentprocessing.application.domain.Payment;
import com.buildcomplete.examples.modularcqrsddd.paymentprocessing.application.domain.PaymentFactory;
import com.buildcomplete.examples.modularcqrsddd.paymentprocessing.ports.repository.PaymentDto;
import com.buildcomplete.examples.modularcqrsddd.paymentprocessing.ports.repository.PaymentDtoRepositoryPort;
import com.buildcomplete.examples.modularcqrsddd.hexagoncore.application.domainsharedkernel.OrderId;
import com.buildcomplete.examples.modularcqrsddd.paymentprocessing.ports.service.PaymentManagerPort;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional("transactionManager")
@RequiredArgsConstructor
class PaymentManagementApplicationService implements PaymentManagerPort {
    private final PaymentDtoConverter paymentDtoConverter;
    private final PaymentFactory paymentFactory;
    private final PaymentDtoRepositoryPort paymentDtoRepositoryPort;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public UUID startPayment(UUID orderId) {
        DomainAggregateChange<Payment> aggregateChange = paymentFactory.startPayment(OrderId.of(orderId));
        savePayment(aggregateChange.getChangedAggregate());
        aggregateChange.getDomainEvents().forEach(eventPublisher::publishEvent);
        return aggregateChange.getChangedAggregate().getId().getValue();
    }

    @Override
    public void completePayment(String brokerPaymentId) {
        Payment payment = getPaymentByBrokerPaymentId(brokerPaymentId);
        DomainAggregateChange<Payment> aggregateChange = payment.complete();
        savePayment(aggregateChange.getChangedAggregate());
        aggregateChange.getDomainEvents().forEach(eventPublisher::publishEvent);
    }

    private Payment getPaymentByBrokerPaymentId(String brokerPaymentId) {
        PaymentDto paymentDto = paymentDtoRepositoryPort.findByBrokerPaymentId(brokerPaymentId).orElseThrow(() -> new IllegalStateException("Payment should exist"));
        return paymentDtoConverter.convert(paymentDto);
    }

    private void savePayment(Payment payment) {
        PaymentDto paymentDto = paymentDtoConverter.convert(payment);
        paymentDtoRepositoryPort.save(paymentDto);
    }
}
