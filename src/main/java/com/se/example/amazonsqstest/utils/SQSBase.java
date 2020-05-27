package com.se.example.amazonsqstest.utils;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.CreateQueueRequest;
import com.amazonaws.services.sqs.model.CreateQueueResult;
import com.amazonaws.services.sqs.model.DeleteQueueResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component("sqs_base")
public class SQSBase {

   private static final Logger logger = LoggerFactory.getLogger(SQSBase.class);

    AmazonSQS client;
    final Map<String, String> attributes = new HashMap<String, String>();
    final CreateQueueRequest createRequest = new CreateQueueRequest("MyQueue").withAttributes(attributes);
//
    @Value("${aws.accessKey}")
    private String awsAccessKey;

    @Value("${aws.secretKey}")
    private String awsSecretKey;

    @Value("${aws.region}")
    private String awsRegion;
//
    private AmazonSQS amazonSQS;

    @PostConstruct
    private void postConstructor() {
         logger.info("SQS Base: post construct start");
        Regions regionItem = Regions.fromName(awsRegion);//"us-west-2");

        AWSCredentials credentials = new BasicAWSCredentials(
                awsAccessKey,
                awsSecretKey
        );

        AWSCredentialsProvider awsCredentialsProvider = new AWSStaticCredentialsProvider(
                new BasicAWSCredentials(awsAccessKey, awsSecretKey)
        );

        this.amazonSQS = AmazonSQSClientBuilder.standard()
                .withRegion(regionItem)
                .withCredentials(awsCredentialsProvider).build();

        this.client = AmazonSQSClientBuilder.standard()
                .withRegion(regionItem)
                .withCredentials(awsCredentialsProvider).build();

        logger.info("SQS Base: post construct end");
    }


    public void createQueue() {
        // Enable server-side encryption by specifying the alias for the
        // AWS managed CMK (customer master key ) for Amazon SQS.
        final String kmsMasterKeyAlias = "alias/aws/sqs";

        attributes.put("KmsMasterKeyId", kmsMasterKeyAlias);

        // (Optional) Specify the length of time, in seconds, for which Amazon SQS can reuse
        attributes.put("KmsDataKeyReusePeriodSeconds", "60");


        CreateQueueRequest createRequest = new CreateQueueRequest("MyQueue2").withAttributes(attributes);
        CreateQueueResult createResult = client.createQueue(createRequest);

        logger.info("Create queue result ");
        logger.info(createResult.toString());

       // DeleteQueueResult deleteQueueResultRequest = new DeleteQueueResult("MyQueue2").withAttributes(attributes);
        DeleteQueueResult deleteQueueResult =  client.deleteQueue(createResult.getQueueUrl());

        logger.info("Delete queue result ");
        logger.info(deleteQueueResult.toString());

        client.listQueues().getQueueUrls().stream().forEach(s -> System.out.println(s));

        int a = 0;

    }
}
