package com.se.example.amazonsqstest.config;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringCloudAwsConfig {

    @Value("${aws.accessKey}")
    private String awsAccessKey;

    @Value("${aws.secretKey}")
    private String awsSecretKey;

    @Value("${aws.region}")
    private String awsRegion;

    // AmazonSQSAsync client  is available by default in the application context when using Spring Boot starters
    @Bean
    public QueueMessagingTemplate queueMessagingTemplate(AmazonSQSAsync amazonSQSAsync) {
        return new QueueMessagingTemplate(amazonSQSAsync);
    }

//    @Bean
//    @ConditionalOnMissingBean(AmazonSQSAsync.class)
//    public AmazonSQSAsync amazonSQSAsync() {
//
////        return AmazonSQSAsyncClientBuilder.defaultClient();
//
//        Regions regionItem = Regions.fromName(awsRegion);
//
//        AWSCredentialsProvider awsCredentialsProvider = new AWSStaticCredentialsProvider(
//                new BasicAWSCredentials(awsAccessKey, awsSecretKey)
//        );
//
//        return  AmazonSQSAsyncClientBuilder.standard()
//                .withRegion(regionItem)
//                .withCredentials(awsCredentialsProvider).build();
//    }
}


