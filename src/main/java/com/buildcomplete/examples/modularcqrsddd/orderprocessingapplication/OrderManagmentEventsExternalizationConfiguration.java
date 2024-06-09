package com.buildcomplete.examples.modularcqrsddd.orderprocessingapplication;

import com.buildcomplete.examples.modularcqrsddd.domainsharedkernel.OrderPayedEvent;
import com.buildcomplete.examples.modularcqrsddd.integrationevents.DomainEventExternalizationConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class OrderManagmentEventsExternalizationConfiguration {

  @Bean
  DomainEventExternalizationConfiguration<OrderPayedEvent> orderPayedEventExternalizationConfiguration(
      @Value("${notifications.target}") String notificationsTarget) {
    return new DomainEventExternalizationConfiguration<>(
        OrderPayedEvent.class,
        event -> notificationsTarget,
        event -> event.getOrderId().getValue().toString());
  }
}
