package com.buildcomplete.examples.modularcqrsddd.write.adapters.sqs.integrationevents;

import com.buildcomplete.examples.modularcqrsddd.write.application.integrationevents.IntegrationEvent;
import com.buildcomplete.examples.modularcqrsddd.write.application.integrationevents.IntegrationEventHandler;
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
