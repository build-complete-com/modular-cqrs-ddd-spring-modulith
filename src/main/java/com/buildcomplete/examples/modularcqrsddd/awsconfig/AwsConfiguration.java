package com.buildcomplete.examples.modularcqrsddd.awsconfig;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class AwsConfiguration {
    @Bean
    AwsClientBuilder.EndpointConfiguration sqsEndpointConfiguration(
            @Value("${cloud.aws.endpoint}") String endpoint,
            @Value("${cloud.aws.region.static}") String region) {
        return new AwsClientBuilder.EndpointConfiguration(endpoint, region);
    }

    @Bean
    AWSCredentialsProvider credentialsProvider(
            @Value("${cloud.aws.credentials.accessKey}") String accessKey,
            @Value("${cloud.aws.credentials.secretKey}") String secretKey) {
        return new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey));
    }
}
