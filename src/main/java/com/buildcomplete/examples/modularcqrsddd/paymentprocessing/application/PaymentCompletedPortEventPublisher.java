package com.buildcomplete.examples.modularcqrsddd.paymentprocessing.application;

import com.buildcomplete.examples.modularcqrsddd.domainsharedkernel.PaymentCompletedEvent;
import com.buildcomplete.examples.modularcqrsddd.paymentprocessing.ports.events.PaymentCompletedPortEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class PaymentCompletedPortEventPublisher {
  private final PaymentCompletedPortEventConverter portEventConverter;
  private final ApplicationEventPublisher eventPublisher;

  @EventListener
  void republishAsPortEvent(PaymentCompletedEvent event) {
    PaymentCompletedPortEvent portEvent = portEventConverter.convert(event);
    eventPublisher.publishEvent(portEvent);
  }
}
