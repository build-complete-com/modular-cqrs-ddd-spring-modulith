package com.buildcomplete.examples.modularcqrsddd.paymentprocessingapplication;

import com.buildcomplete.examples.modularcqrsddd.domainsharedkernel.PaymentCompletedEvent;
import com.buildcomplete.examples.modularcqrsddd.integrationevents.ByTypeEventExternalizationConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class PaymentManagmentEventsExternalizationConfiguration {

  @Bean
  ByTypeEventExternalizationConfiguration<PaymentCompletedEvent> paymentCompletedEventExternalizationConfiguration(
      @Value("${notifications.target}") String notificationsTarget) {
    return new ByTypeEventExternalizationConfiguration<>(
        PaymentCompletedEvent.class,
        event -> notificationsTarget,
        event -> event.getPaymentId().getValue().toString());
  }
}
