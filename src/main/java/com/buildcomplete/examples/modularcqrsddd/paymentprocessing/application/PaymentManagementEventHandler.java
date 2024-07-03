package com.buildcomplete.examples.modularcqrsddd.paymentprocessing.application;

import com.buildcomplete.examples.modularcqrsddd.hexagoncore.application.domainframework.DomainAggregateChange;
import com.buildcomplete.examples.modularcqrsddd.paymentprocessing.application.domain.Payment;
import com.buildcomplete.examples.modularcqrsddd.paymentprocessing.application.domain.PaymentStartedEvent;
import com.buildcomplete.examples.modularcqrsddd.paymentprocessing.ports.paymentbrokerapi.PaymentBroker;
import com.buildcomplete.examples.modularcqrsddd.paymentprocessing.ports.repository.PaymentDto;
import com.buildcomplete.examples.modularcqrsddd.paymentprocessing.ports.repository.PaymentDtoRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class PaymentManagementEventHandler {
    private final PaymentDtoConverter paymentDtoConverter;
    private final PaymentDtoRepository paymentDtoRepository;
    private final PaymentBroker paymentBroker;
    private final ApplicationEventPublisher eventPublisher;

    @ApplicationModuleListener
    void handleEvent(PaymentStartedEvent event) {
        Payment payment = getPaymentById(event.getPaymentId().getValue());
        String brokerPaymentId = paymentBroker.getBrokerPaymentId();
        DomainAggregateChange<Payment> aggregateChange = payment.assignBrokerPaymentId(brokerPaymentId);
        savePayment(aggregateChange.getChangedAggregate());
        aggregateChange.getDomainEvents().forEach(eventPublisher::publishEvent);
    }

    Payment getPaymentById(UUID paymentId) {
        PaymentDto paymentDto = paymentDtoRepository.findById(paymentId).orElseThrow(() -> new IllegalStateException("Payment should exist"));
        return paymentDtoConverter.convert(paymentDto);
    }

    void savePayment(Payment payment) {
        PaymentDto paymentDto = paymentDtoConverter.convert(payment);
        paymentDtoRepository.save(paymentDto);
    }
}
