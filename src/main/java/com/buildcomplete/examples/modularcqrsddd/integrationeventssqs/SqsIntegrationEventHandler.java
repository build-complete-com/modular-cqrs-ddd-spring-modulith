package com.buildcomplete.examples.modularcqrsddd.integrationeventssqs;

import com.buildcomplete.examples.modularcqrsddd.domainframework.DomainEvent;
import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class SqsIntegrationEventHandler {
  private final ApplicationEventPublisher applicationEventPublisher;

  @SqsListener(value = "${sqs.queue-name}")
  public void handleEvent(DomainEvent domainEvent) {
    applicationEventPublisher.publishEvent(domainEvent);
  }
}
