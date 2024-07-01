package com.buildcomplete.examples.modularcqrsddd.paymentprocessing.application;

import com.buildcomplete.examples.modularcqrsddd.paymentprocessing.application.domain.PaymentCompletedEvent;
import com.buildcomplete.examples.modularcqrsddd.paymentprocessing.ports.events.PaymentCompletedPortEvent;
import org.springframework.stereotype.Service;

@Service
class PaymentCompletedPortEventConverter {
  PaymentCompletedPortEvent convert(PaymentCompletedEvent event) {
    return new PaymentCompletedPortEvent(event.getPaymentId().getValue(), event.getOrderId().getValue());
  }
}
