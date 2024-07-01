package com.buildcomplete.examples.modularcqrsddd.paymentprocessing.application;

import com.buildcomplete.examples.modularcqrsddd.domainsharedkernel.OrderId;
import com.buildcomplete.examples.modularcqrsddd.domainsharedkernel.PaymentId;
import com.buildcomplete.examples.modularcqrsddd.paymentprocessing.application.domain.Payment;
import com.buildcomplete.examples.modularcqrsddd.paymentprocessing.ports.repository.PaymentDto;
import org.springframework.stereotype.Service;

@Service
class PaymentDtoConverter {

  PaymentDto convert(Payment payment) {
    return new PaymentDto(
        payment.getId().getValue(),
        payment.getOrderId().getValue(),
        payment.getBrokerPaymentId(),
        payment.isComplete(),
        payment.getVersion());
  }

  public Payment convert(PaymentDto paymentDto) {
    return Payment.reconstitutingBuilder()
        .id(PaymentId.of(paymentDto.getId()))
        .orderId(OrderId.of(paymentDto.getOrderId()))
        .brokerPaymentId(paymentDto.getBrokerPaymentId())
        .complete(paymentDto.isComplete())
        .version(paymentDto.getVersion())
        .build();
  }
}
