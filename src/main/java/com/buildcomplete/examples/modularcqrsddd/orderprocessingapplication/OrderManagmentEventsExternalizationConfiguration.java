package com.buildcomplete.examples.modularcqrsddd.orderprocessingapplication;

import com.buildcomplete.examples.modularcqrsddd.domainsharedkernel.OrderPayedEvent;
import com.buildcomplete.examples.modularcqrsddd.integrationevents.DomainEventExternalizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class OrderManagmentEventsExternalizationConfiguration {

  @Bean
  DomainEventExternalizer<OrderPayedEvent> orderPayedEventExternalizer(
      @Value("${notifications.target}") String notificationsTarget) {
    return new DomainEventExternalizer<>(
        OrderPayedEvent.class,
        event -> notificationsTarget,
        event -> event.getOrderId().getValue().toString());
  }
}
