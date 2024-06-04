package com.buildcomplete.examples.modularcqrsddd.integrationeventssns;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;
import io.awspring.cloud.sns.core.SnsTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import software.amazon.awssdk.services.sns.SnsClient;

@Configuration
class SnsConfiguration {
    @Bean
    SnsTemplate snsTemplate(SnsClient snsClient) {
        MappingJackson2MessageConverter jackson2MessageConverter = getMappingJackson2MessageConverter();
        return new SnsTemplate(snsClient, jackson2MessageConverter);
    }

    private MappingJackson2MessageConverter getMappingJackson2MessageConverter() {
        MappingJackson2MessageConverter messageConverter = new MappingJackson2MessageConverter();
        messageConverter.setObjectMapper(messagingObjectMapper());
        messageConverter.setStrictContentTypeMatch(false);
        messageConverter.setSerializedPayloadClass(String.class);
        return messageConverter;
    }

    private ObjectMapper messagingObjectMapper() {
        PolymorphicTypeValidator ptv = BasicPolymorphicTypeValidator.builder()
            .allowIfSubType("com.buildcomplete.examples.modularcqrsddd")
            .build();
        ObjectMapper mapper = new ObjectMapper();
        mapper.activateDefaultTyping(ptv, ObjectMapper.DefaultTyping.EVERYTHING);
        return mapper;
    }
}
