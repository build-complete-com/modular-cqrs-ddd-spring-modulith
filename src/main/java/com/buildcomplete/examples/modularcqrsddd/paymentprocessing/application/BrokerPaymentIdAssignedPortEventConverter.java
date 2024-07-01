package com.buildcomplete.examples.modularcqrsddd.paymentprocessing.application;

import com.buildcomplete.examples.modularcqrsddd.paymentprocessing.application.domain.BrokerPaymentIdAssignedEvent;
import com.buildcomplete.examples.modularcqrsddd.paymentprocessing.ports.events.BrokerPaymentIdAssignedPortEvent;
import org.springframework.stereotype.Service;

@Service
class BrokerPaymentIdAssignedPortEventConverter {
  BrokerPaymentIdAssignedPortEvent convert(BrokerPaymentIdAssignedEvent domainEvent) {
    return new BrokerPaymentIdAssignedPortEvent(domainEvent.getPaymentId().getValue(), domainEvent.getBrokerPaymentId());
  }
}
