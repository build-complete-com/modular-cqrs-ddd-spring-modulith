package com.buildcomplete.examples.modularcqrsddd.paymentprocessing.application;

import com.buildcomplete.examples.modularcqrsddd.paymentprocessing.application.domain.PaymentStartedEvent;
import com.buildcomplete.examples.modularcqrsddd.paymentprocessing.ports.events.PaymentStartedPortEvent;
import org.springframework.stereotype.Service;

@Service
class PaymentStartedPortEventConverter {
  PaymentStartedPortEvent convert(PaymentStartedEvent event) {
    return new PaymentStartedPortEvent(event.getPaymentId().getValue());
  }
}
