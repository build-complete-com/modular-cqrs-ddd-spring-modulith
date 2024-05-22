package com.buildcomplete.examples.modularcqrsddd.domain.paymentprocessing;

import com.buildcomplete.examples.modularcqrsddd.domain.framework.DomainAggregateChange;
import com.buildcomplete.examples.modularcqrsddd.domain.framework.DomainAggregateRoot;
import com.buildcomplete.examples.modularcqrsddd.domain.sharedkernel.OrderId;
import com.buildcomplete.examples.modularcqrsddd.domain.sharedkernel.PaymentCompletedEvent;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
public class Payment extends DomainAggregateRoot<PaymentId> {
    private final OrderId orderId;
    private final String brokerPaymentId;
    private final boolean complete;

    @Builder(builderMethodName = "reconstitutingBuilder", builderClassName = "ReconstitutingBuilder", toBuilder = true)
    public Payment(PaymentId id, Long version, OrderId orderId, String brokerPaymentId, boolean complete) {
        super(id, version);
        this.orderId = orderId;
        this.brokerPaymentId = brokerPaymentId;
        this.complete = complete;
    }

    @Builder(builderMethodName = "constitutingBuilder", builderClassName = "ConstitutingBuilder")
    public Payment(OrderId orderId, String brokerPaymentId, boolean complete) {
        super(PaymentId.randomPaymentId(), null);
        this.orderId = orderId;
        this.brokerPaymentId = brokerPaymentId;
        this.complete = complete;
    }

    public DomainAggregateChange<Payment> assignBrokerPaymentId(String brokerPaymentId) {
        Payment changedPayment = this.toBuilder()
                .brokerPaymentId(brokerPaymentId)
                .build();
        BrokerPaymentIdAssignedEvent brokerPaymentIdAssignedEvent = new BrokerPaymentIdAssignedEvent(changedPayment);
        return DomainAggregateChange.<Payment>builder()
                .changedAggregate(changedPayment)
                .domainEvents(List.of(brokerPaymentIdAssignedEvent))
                .build();
    }

    public DomainAggregateChange<Payment> complete() {
        Payment changedPayment = this.toBuilder()
                .complete(true)
                .build();
        PaymentCompletedEvent paymentCompletedEvent = new PaymentCompletedEvent(changedPayment);
        return DomainAggregateChange.<Payment>builder()
                .changedAggregate(changedPayment)
                .domainEvents(List.of(paymentCompletedEvent))
                .build();
    }
}
