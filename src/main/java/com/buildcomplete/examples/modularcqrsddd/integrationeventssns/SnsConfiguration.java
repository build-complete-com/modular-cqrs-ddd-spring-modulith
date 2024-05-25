package com.buildcomplete.examples.modularcqrsddd.integrationeventssns;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.awspring.cloud.core.env.ResourceIdResolver;
import io.awspring.cloud.messaging.core.NotificationMessagingTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;

@Configuration
class SnsConfiguration {
    @Bean
    NotificationMessagingTemplate notificationMessagingTemplate(AmazonSNS amazonSNS, ObjectMapper objectMapper) {
        MappingJackson2MessageConverter mappingJackson2MessageConverter = new MappingJackson2MessageConverter();
        mappingJackson2MessageConverter.setSerializedPayloadClass(String.class);
        mappingJackson2MessageConverter.setObjectMapper(objectMapper);
        return new NotificationMessagingTemplate(amazonSNS, (ResourceIdResolver) null, mappingJackson2MessageConverter);
    }

    @Bean
    @Primary
    AmazonSNS amazonSNS(AWSCredentialsProvider awsCredentialsProvider, AwsClientBuilder.EndpointConfiguration endpointConfiguration) {
        return AmazonSNSClientBuilder
                .standard()
                .withEndpointConfiguration(endpointConfiguration)
                .withCredentials(awsCredentialsProvider)
                .build();
    }
}
