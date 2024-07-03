package com.buildcomplete.examples.modularcqrsddd.paymentprocessing.application.domain;

import com.buildcomplete.examples.modularcqrsddd.hexagoncore.application.domainframework.DomainAggregateChange;
import com.buildcomplete.examples.modularcqrsddd.hexagoncore.application.domainsharedkernel.OrderId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PaymentFactory {
    public DomainAggregateChange<Payment> startPayment(OrderId orderId) {
        Payment payment = Payment.constitutingBuilder()
                .orderId(orderId)
                .brokerPaymentId(null)
                .complete(false)
                .build();
        PaymentStartedEvent paymentStartedEvent = new PaymentStartedEvent(payment);
        return DomainAggregateChange.<Payment>builder()
                .changedAggregate(payment)
                .domainEvents(List.of(paymentStartedEvent))
                .build();
    }
}
