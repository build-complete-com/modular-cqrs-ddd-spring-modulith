package com.buildcomplete.examples.modularcqrsddd.orderprocessing.application;

import com.buildcomplete.examples.modularcqrsddd.hexagoncore.application.integrationevents.ByTypeEventExternalizationConfiguration;
import com.buildcomplete.examples.modularcqrsddd.orderprocessing.ports.events.OrderPayedPortEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class OrderManagementEventsExternalizationConfiguration {

  @Bean
  ByTypeEventExternalizationConfiguration<OrderPayedPortEvent> orderPayedEventExternalizationConfiguration(
      @Value("${notifications.target}") String notificationsTarget) {
    return new ByTypeEventExternalizationConfiguration<>(
        OrderPayedPortEvent.class,
        event -> notificationsTarget,
        event -> event.getOrderId().toString());
  }
}
