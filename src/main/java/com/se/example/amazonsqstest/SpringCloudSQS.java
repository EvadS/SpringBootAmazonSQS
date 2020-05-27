package com.se.example.amazonsqstest;

import com.se.example.amazonsqstest.models.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.config.annotation.EnableSqs;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.context.annotation.Lazy;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


@Component
@EnableSqs
//@EnableAsync
public class SpringCloudSQS {
    private static final Logger logger = LoggerFactory.getLogger(SpringCloudSQS.class);

    static final String QUEUE_NAME = "https://sqs.us-east-2.amazonaws.com/484959649436/DevelopesQueue";



//    @SqsListener(QUEUE_NAME)
//    public void receiveMessage(String message, @Header("SenderId") String senderId) {
//        logger.info("---  Received message: {}, having SenderId: {}", message, senderId);
//    }


//    @Async
    @SqsListener(value = QUEUE_NAME, deletionPolicy = SqsMessageDeletionPolicy.NEVER)
    public void process( final  Person person, @Header("SenderId") String senderId) throws Exception {

        logger.info("---  Received message: {}, having SenderId: {}", person, senderId);

        //...
    }



    public void send(String queueName, Object message) {
      //  queueMessagingTemplate.convertAndSend(queueName, message);
    }
}
