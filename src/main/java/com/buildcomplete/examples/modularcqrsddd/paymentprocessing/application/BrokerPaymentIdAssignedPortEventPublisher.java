package com.buildcomplete.examples.modularcqrsddd.paymentprocessing.application;

import com.buildcomplete.examples.modularcqrsddd.paymentprocessing.application.domain.BrokerPaymentIdAssignedEvent;
import com.buildcomplete.examples.modularcqrsddd.paymentprocessing.application.domain.PaymentStartedEvent;
import com.buildcomplete.examples.modularcqrsddd.paymentprocessing.ports.events.BrokerPaymentIdAssignedPortEvent;
import com.buildcomplete.examples.modularcqrsddd.paymentprocessing.ports.events.PaymentStartedPortEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class BrokerPaymentIdAssignedPortEventPublisher {
  private final BrokerPaymentIdAssignedPortEventConverter portEventConverter;
  private final ApplicationEventPublisher eventPublisher;

  @EventListener
  void republishAsPortEvent(BrokerPaymentIdAssignedEvent domainEvent) {
    BrokerPaymentIdAssignedPortEvent portEvent = portEventConverter.convert(domainEvent);
    eventPublisher.publishEvent(portEvent);
  }
}
