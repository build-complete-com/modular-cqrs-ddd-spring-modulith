package com.buildcomplete.examples.modularcqrsddd.orderprocessing.application;

import com.buildcomplete.examples.modularcqrsddd.domainsharedkernel.OrderPayedEvent;
import com.buildcomplete.examples.modularcqrsddd.integrationevents.ByTypeEventExternalizationConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class OrderManagmentEventsExternalizationConfiguration {

  @Bean
  ByTypeEventExternalizationConfiguration<OrderPayedEvent> orderPayedEventExternalizationConfiguration(
      @Value("${notifications.target}") String notificationsTarget) {
    return new ByTypeEventExternalizationConfiguration<>(
        OrderPayedEvent.class,
        event -> notificationsTarget,
        event -> event.getOrderId().getValue().toString());
  }
}
