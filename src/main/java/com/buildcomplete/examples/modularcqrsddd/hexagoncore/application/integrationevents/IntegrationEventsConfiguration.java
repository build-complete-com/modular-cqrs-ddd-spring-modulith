package com.buildcomplete.examples.modularcqrsddd.hexagoncore.application.integrationevents;

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
  <T> EventExternalizationConfiguration externalizationConfiguration(
      List<ByTypeEventExternalizationConfiguration<T>> byTypeEventExternalizationConfigurations) {
    EventExternalizationConfiguration.Router router = EventExternalizationConfiguration.externalizing()
        .selectByType(testedEventClass -> getExternalizerBy(testedEventClass, byTypeEventExternalizationConfigurations).isPresent());
    router = router.routeAll(event -> {
      Class<T> eventClass = (Class<T>) event.getClass();
      ByTypeEventExternalizationConfiguration<T>
          eventExternalizer = getExternalizerBy(eventClass, byTypeEventExternalizationConfigurations).get();
      return RoutingTarget.forTarget(eventExternalizer.getTargetProvider().apply((T) event)).withoutKey();
    });
    for (ByTypeEventExternalizationConfiguration<T> externalizer : byTypeEventExternalizationConfigurations) {
      final EventExternalizationConfiguration.Router tmpRouter = router;
      router = externalizer.getRoutingKeyProvider().map(routingKeyProvider -> {
        return tmpRouter.routeKey(externalizer.getEventClass(), routingKeyProvider);
      }).orElse(router);
    }
    return router.build();
  }

  private <T> Optional<ByTypeEventExternalizationConfiguration<T>> getExternalizerBy(
      Class<?> testedEventClass,
      List<ByTypeEventExternalizationConfiguration<T>> externalizers) {
    return externalizers.stream()
        .filter(externalizer -> externalizer.getEventClass().equals(testedEventClass))
        .findAny();
  }
}
