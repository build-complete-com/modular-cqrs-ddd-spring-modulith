package com.buildcomplete.examples.modularcqrsddd.paymentprocessingmongodb;

import com.buildcomplete.examples.modularcqrsddd.paymentprocessing.ports.repository.PaymentDto;
import org.springframework.stereotype.Component;

@Component
class PaymentDocumentConverter {

    PaymentDto convert(PaymentDocument document) {
        return new PaymentDto(
            document.getId(),
            document.getOrderId(),
            document.getBrokerPaymentId(),
            document.isComplete(),
            document.getVersion()
        );
    }

    PaymentDocument convert(PaymentDto paymentDto) {
        PaymentDocument document = new PaymentDocument();
        document.setId(paymentDto.getId());
        document.setVersion(paymentDto.getVersion());
        document.setOrderId(paymentDto.getOrderId());
        document.setBrokerPaymentId(paymentDto.getBrokerPaymentId());
        document.setComplete(paymentDto.isComplete());
        return document;
    }
}
