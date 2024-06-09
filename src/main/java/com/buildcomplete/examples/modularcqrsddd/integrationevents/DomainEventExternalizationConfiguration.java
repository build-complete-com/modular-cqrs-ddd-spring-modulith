package com.buildcomplete.examples.modularcqrsddd.integrationevents;

import com.buildcomplete.examples.modularcqrsddd.domainframework.DomainEvent;
import java.util.Optional;
import java.util.function.Function;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class DomainEventExternalizationConfiguration<T extends DomainEvent> {
  private final Class<T> eventClass;
  private final Function<T, String> targetProvider;
  private final Function<T, String> routingKeyProvider;

  public Optional<Function<T, String>> getRoutingKeyProvider() {
    return Optional.ofNullable(routingKeyProvider);
  }
}