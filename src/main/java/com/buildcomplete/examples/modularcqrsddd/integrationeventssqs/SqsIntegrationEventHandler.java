package com.buildcomplete.examples.modularcqrsddd.integrationeventssqs;

import com.buildcomplete.examples.modularcqrsddd.integrationevents.IntegrationEvent;
import com.buildcomplete.examples.modularcqrsddd.integrationevents.IntegrationEventHandler;
import io.awspring.cloud.messaging.listener.SqsMessageDeletionPolicy;
import io.awspring.cloud.messaging.listener.annotation.SqsListener;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class SqsIntegrationEventHandler {
  private final IntegrationEventHandler integrationEventHandler;

  @SqsListener(value = "${sqs.queue-name}", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
  public void handleEvent(IntegrationEvent integrationEvent) {
    integrationEventHandler.handleEvent(integrationEvent);
  }
}
