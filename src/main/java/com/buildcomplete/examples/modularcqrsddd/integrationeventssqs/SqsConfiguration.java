package com.buildcomplete.examples.modularcqrsddd.integrationeventssqs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;
import io.awspring.cloud.sqs.config.SqsListenerConfigurer;
import java.util.UUID;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.PayloadMethodArgumentResolver;

@Configuration
class SqsConfiguration {

    @Bean
    SqsListenerConfigurer sqsListenerConfigurer() {
        return registrar -> registrar.manageMethodArgumentResolvers(resolvers -> {
            resolvers.removeIf(PayloadMethodArgumentResolver.class::isInstance);
            resolvers.add(getPayloadArgumentResolver());
        });
    }

    private PayloadMethodArgumentResolver getPayloadArgumentResolver() {
        MappingJackson2MessageConverter messageConverter = new MappingJackson2MessageConverter();
        messageConverter.setObjectMapper(messagingObjectMapper());
        messageConverter.setStrictContentTypeMatch(false);
        PayloadMethodArgumentResolver payloadArgumentResolver = new PayloadMethodArgumentResolver(messageConverter);
        return payloadArgumentResolver;
    }

    private ObjectMapper messagingObjectMapper() {
        PolymorphicTypeValidator ptv = BasicPolymorphicTypeValidator.builder()
            .allowIfSubType("com.buildcomplete.examples.modularcqrsddd")
            .allowIfBaseType(UUID.class)
            .build();
        ObjectMapper mapper = new ObjectMapper();
        mapper.activateDefaultTyping(ptv, ObjectMapper.DefaultTyping.EVERYTHING);
        return mapper;
    }
}
