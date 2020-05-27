package com.se.example.amazonsqstest;

import com.google.gson.Gson;
import com.se.example.amazonsqstest.models.Person;
import com.se.example.amazonsqstest.models.SamsungPhone;
import com.se.example.amazonsqstest.utils.SQSBase;
import com.se.example.amazonsqstest.utils.SQSUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class AmazonSqsDemoApplication implements CommandLineRunner {

    @Autowired
    private SQSUtil sqsUtil;

    @Autowired
    private SQSBase sqsBase;

    @Autowired
    private QueueMessagingTemplate queueMessagingTemplate;

    public static void main(String[] args) {
        SpringApplication.run(AmazonSqsDemoApplication.class, args);
    }

    @Override
    public void run(String... args) {

      queueMessagingTemplate.convertAndSend(
              "https://sqs.us-east-2.amazonaws.com/484959649436/DevelopesQueue",
              new Person("John", "Doe"));
int aaa = 10;

    }

    private void sendPhonesList() {
        List<SamsungPhone> samsungPhones = new ArrayList<>();

        SamsungPhone galaxyNote10Plus = new SamsungPhone();
        galaxyNote10Plus.setName("Samsung Galaxy Note 10 Plus");
        galaxyNote10Plus.setDescription("2019 flagship phone with a 6.8 inch Super AMOLED display, S Pen, and much more");
        galaxyNote10Plus.setTimestamp(System.currentTimeMillis());
        samsungPhones.add(galaxyNote10Plus);

        SamsungPhone galaxyNote10 = new SamsungPhone();
        galaxyNote10.setName("Samsung Galaxy Note 10");
        galaxyNote10.setDescription("2019 flagship phone with a 6.5 inch Super AMOLED display, S Pen, and much more");
        galaxyNote10.setTimestamp(System.currentTimeMillis());
        samsungPhones.add(galaxyNote10);

        SamsungPhone galaxyS10Plus = new SamsungPhone();
        galaxyS10Plus.setName("Samsung Galaxy S 10 Plus");
        galaxyS10Plus.setDescription("Early 2019 flagship phone with a 6.5 inch Super AMOLED display, " +
                "dual punch hole selfie cameras, and much more");
        galaxyS10Plus.setTimestamp(System.currentTimeMillis());
        samsungPhones.add(galaxyS10Plus);

        SamsungPhone galaxyS10 = new SamsungPhone();
        galaxyS10.setName("Samsung Galaxy S 10");
        galaxyS10.setDescription("Early 2019 flagship phone with a 6.3 inch Super AMOLED display, " +
                "dual punch hole selfie cameras, and much more");
        galaxyS10.setTimestamp(System.currentTimeMillis());
        samsungPhones.add(galaxyS10);

        for (SamsungPhone samsungPhone : samsungPhones) {
            this.sqsUtil.sendSQSMessage(new Gson().toJson(samsungPhone));
        }
    }
}
