package com.buildcomplete.examples.modularcqrsddd.write.adapters.sns.integrationevents;

import com.buildcomplete.examples.modularcqrsddd.write.application.integrationevents.IntegrationEvent;
import com.buildcomplete.examples.modularcqrsddd.write.application.integrationevents.IntegrationEventPublisher;
import io.awspring.cloud.messaging.core.NotificationMessagingTemplate;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
class SnsIntegrationEventPublisher implements IntegrationEventPublisher {
    private static final String MESSAGE_DEDUPLICATION_ID = "message-deduplication-id";
    private static final String MESSAGE_GROUP_ID = "message-group-id";

    private final NotificationMessagingTemplate messagingTemplate;
    private final String destinationName;

    public SnsIntegrationEventPublisher(NotificationMessagingTemplate messagingTemplate,
                                        @Value("${sns.topic-name}") String destinationName) {
        this.messagingTemplate = messagingTemplate;
        this.destinationName = destinationName;
    }

    @Override
    public void publishEvent(IntegrationEvent integrationEvent) {
        Map<String, Object> headers = Map.of(
            MESSAGE_DEDUPLICATION_ID, integrationEvent.getDomainEvent().getId().toString(),
            MESSAGE_GROUP_ID, integrationEvent.getMessageGroupId().toString());
        this.messagingTemplate.convertAndSend(this.destinationName, integrationEvent, headers);
    }
}
