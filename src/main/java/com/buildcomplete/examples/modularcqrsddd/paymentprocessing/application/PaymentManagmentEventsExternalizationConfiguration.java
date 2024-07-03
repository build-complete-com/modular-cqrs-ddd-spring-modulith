package com.buildcomplete.examples.modularcqrsddd.paymentprocessing.application;

import com.buildcomplete.examples.modularcqrsddd.hexagoncore.application.integrationevents.ByTypeEventExternalizationConfiguration;
import com.buildcomplete.examples.modularcqrsddd.paymentprocessing.ports.events.PaymentCompletedPortEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class PaymentManagmentEventsExternalizationConfiguration {

  @Bean
  ByTypeEventExternalizationConfiguration<PaymentCompletedPortEvent> paymentCompletedEventExternalizationConfiguration(
      @Value("${notifications.target}") String notificationsTarget) {
    return new ByTypeEventExternalizationConfiguration<>(
        PaymentCompletedPortEvent.class,
        event -> notificationsTarget,
        event -> event.getPaymentId().toString());
  }
}
