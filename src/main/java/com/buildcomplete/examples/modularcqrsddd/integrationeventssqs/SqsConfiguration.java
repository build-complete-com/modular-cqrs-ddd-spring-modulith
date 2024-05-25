package com.buildcomplete.examples.modularcqrsddd.integrationeventssqs;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.awspring.cloud.messaging.config.QueueMessageHandlerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.PayloadMethodArgumentResolver;

import java.util.List;

@Configuration
class SqsConfiguration {
    @Bean
    @Primary
    @ConditionalOnBean(AwsClientBuilder.EndpointConfiguration.class)
    AmazonSQSAsync amazonSQSAsync(AWSCredentialsProvider awsCredentialsProvider, AwsClientBuilder.EndpointConfiguration sqsEndpointConfiguration) {
        return AmazonSQSAsyncClientBuilder
                .standard()
                .withEndpointConfiguration(sqsEndpointConfiguration)
                .withCredentials(awsCredentialsProvider)
                .build();
    }

    @Bean
    QueueMessageHandlerFactory queueMessageHandlerFactory(ObjectMapper objectMapper) {
        QueueMessageHandlerFactory factory = new QueueMessageHandlerFactory();
        PayloadMethodArgumentResolver payloadArgumentResolver = getPayloadArgumentResolver(objectMapper);
        factory.setArgumentResolvers(List.of(payloadArgumentResolver));
        return factory;
    }

    private PayloadMethodArgumentResolver getPayloadArgumentResolver(ObjectMapper objectMapper) {
        MappingJackson2MessageConverter messageConverter = new MappingJackson2MessageConverter();
        messageConverter.setObjectMapper(objectMapper);
        messageConverter.setStrictContentTypeMatch(false);
        PayloadMethodArgumentResolver payloadArgumentResolver = new PayloadMethodArgumentResolver(messageConverter);
        return payloadArgumentResolver;
    }
}
