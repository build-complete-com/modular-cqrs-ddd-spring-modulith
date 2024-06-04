package com.buildcomplete.examples.modularcqrsddd.paymentprocessingapplication;

import com.buildcomplete.examples.modularcqrsddd.domainsharedkernel.PaymentCompletedEvent;
import com.buildcomplete.examples.modularcqrsddd.integrationevents.DomainEventExternalizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class PaymentManagmentEventsExternalizationConfiguration {

  @Bean
  DomainEventExternalizer<PaymentCompletedEvent> paymentCompletedEventExternalizer(
      @Value("${notifications.target}") String notificationsTarget) {
    return new DomainEventExternalizer<>(
        PaymentCompletedEvent.class,
        event -> notificationsTarget,
        event -> event.getPaymentId().getValue().toString());
  }
}
