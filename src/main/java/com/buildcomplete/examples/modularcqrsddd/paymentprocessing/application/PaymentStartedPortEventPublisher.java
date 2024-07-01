package com.buildcomplete.examples.modularcqrsddd.paymentprocessing.application;

import com.buildcomplete.examples.modularcqrsddd.paymentprocessing.application.domain.PaymentStartedEvent;
import com.buildcomplete.examples.modularcqrsddd.paymentprocessing.ports.events.PaymentStartedPortEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class PaymentStartedPortEventPublisher {
  private final PaymentStartedPortEventConverter portEventConverter;
  private final ApplicationEventPublisher eventPublisher;

  @EventListener
  void republishAsPortEvent(PaymentStartedEvent event) {
    PaymentStartedPortEvent portEvent = portEventConverter.convert(event);
    eventPublisher.publishEvent(portEvent);
  }
}
