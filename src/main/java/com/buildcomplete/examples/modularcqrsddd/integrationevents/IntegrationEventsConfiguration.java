package com.buildcomplete.examples.modularcqrsddd.integrationevents;

import com.buildcomplete.examples.modularcqrsddd.domainframework.DomainEvent;
import java.util.List;
import java.util.Optional;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.modulith.events.EventExternalizationConfiguration;
import org.springframework.modulith.events.RoutingTarget;

@Configuration
class IntegrationEventsConfiguration {

  @Bean
  @Primary
  <T extends DomainEvent> EventExternalizationConfiguration externalizationConfiguration(
      List<DomainEventExternalizer<T>> domainEventExternalizers) {
    EventExternalizationConfiguration.Router router = EventExternalizationConfiguration.externalizing()
        .selectByType(testedEventClass -> getExternalizerBy(testedEventClass, domainEventExternalizers).isPresent());
    router = router.routeAll(event -> {
      Class<T> eventClass = (Class<T>) event.getClass();
      DomainEventExternalizer<T> eventExternalizer = getExternalizerBy(eventClass, domainEventExternalizers).get();
      return RoutingTarget.forTarget(eventExternalizer.getTargetProvider().apply((T) event)).withoutKey();
    });
    for (DomainEventExternalizer<T> externalizer : domainEventExternalizers) {
      final EventExternalizationConfiguration.Router tmpRouter = router;
      router = externalizer.getRoutingKeyProvider().map(routingKeyProvider -> {
        return tmpRouter.routeKey(externalizer.getEventClass(), routingKeyProvider);
      }).orElse(router);
    }
    return router.build();
  }

  private <T extends DomainEvent> Optional<DomainEventExternalizer<T>> getExternalizerBy(
      Class<?> testedEventClass,
      List<DomainEventExternalizer<T>> externalizers) {
    return externalizers.stream()
        .filter(externalizer -> externalizer.getEventClass().equals(testedEventClass))
        .findAny();
  }
}
