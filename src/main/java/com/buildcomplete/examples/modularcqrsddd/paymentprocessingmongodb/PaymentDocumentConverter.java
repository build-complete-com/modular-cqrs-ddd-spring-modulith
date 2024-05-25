package com.buildcomplete.examples.modularcqrsddd.paymentprocessingmongodb;

import com.buildcomplete.examples.modularcqrsddd.paymentprocessingdomain.Payment;
import com.buildcomplete.examples.modularcqrsddd.domainsharedkernel.PaymentId;
import com.buildcomplete.examples.modularcqrsddd.domainsharedkernel.OrderId;
import org.springframework.stereotype.Component;

@Component
class PaymentDocumentConverter {

    Payment convert(PaymentDocument document) {
        return Payment.reconstitutingBuilder()
                .id(PaymentId.of(document.getId()))
                .version(document.getVersion())
                .orderId(OrderId.of(document.getOrderId()))
                .brokerPaymentId(document.getBrokerPaymentId())
                .complete(document.isComplete())
                .build();
    }

    PaymentDocument convert(Payment aggregateRoot) {
        PaymentDocument document = new PaymentDocument();
        document.setId(aggregateRoot.getId().getValue());
        document.setVersion(aggregateRoot.getVersion());
        document.setOrderId(aggregateRoot.getOrderId().getValue());
        document.setBrokerPaymentId(aggregateRoot.getBrokerPaymentId());
        document.setComplete(aggregateRoot.isComplete());
        return document;
    }
}
