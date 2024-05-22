package com.buildcomplete.examples.modularcqrsddd.write.application.paymentprocessing;

import com.buildcomplete.examples.modularcqrsddd.domain.framework.DomainEvent;
import com.buildcomplete.examples.modularcqrsddd.domain.paymentprocessing.BrokerPaymentIdAssignedEvent;
import com.buildcomplete.examples.modularcqrsddd.domain.paymentprocessing.PaymentStartedEvent;
import com.buildcomplete.examples.modularcqrsddd.domain.sharedkernel.PaymentCompletedEvent;
import com.buildcomplete.examples.modularcqrsddd.write.application.integrationevents.IntegrationEvent;
import com.buildcomplete.examples.modularcqrsddd.write.application.integrationevents.IntegrationEventPublisher;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class PaymentManagementIntegrationEventPublisher {
  private final IntegrationEventPublisher integrationEventPublisher;

  @EventListener({
      PaymentStartedEvent.class,
      BrokerPaymentIdAssignedEvent.class,
      PaymentCompletedEvent.class})
  void republishAsIntegrationEvents(DomainEvent domainEvent) {
    UUID messageGroupId = getMessageGroupId(domainEvent);
    IntegrationEvent integrationEvent = new IntegrationEvent(messageGroupId, domainEvent);
    integrationEventPublisher.publishEvent(integrationEvent);
  }

  private UUID getMessageGroupId(DomainEvent domainEvent) {
    if (domainEvent instanceof PaymentStartedEvent castedEvent) {
      return castedEvent.getPaymentId().getValue();
    }
    if (domainEvent instanceof BrokerPaymentIdAssignedEvent castedEvent) {
      return castedEvent.getPaymentId().getValue();
    }
    if (domainEvent instanceof PaymentCompletedEvent castedEvent) {
      return castedEvent.getPaymentId().getValue();
    }
    throw new IllegalArgumentException("Unknown domain event type");
  }
}
