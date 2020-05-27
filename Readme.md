#Amazon 
Spring Cloud AWS provides 
 * Amazon SQS
 * Amazon SNS 
 integration that simplifies the publication and consumption of messages over SQS or SNS. 
 
 
 #sources 
 https://levelup.gitconnected.com/receiving-messages-from-amazon-sqs-in-a-spring-boot-application-6e8a2d7583be
 
 mvn package -DskipTests
 
 java -jar spring-cloud-aws-sample-0.1.0-SNAPSHOT.jar \
 --spring.profiles.active=prod