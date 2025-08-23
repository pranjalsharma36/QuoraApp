package com.QuoraApp.QuoraApp.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListnerGroup {

    private final String topicName = "questionTopic";
    private final String groupId = "kafka-consumer-group";

    @KafkaListener(topics = topicName, groupId = groupId)
    public void listenGroupFoo(String message) {
        System.out.println("Received Message in group : " + groupId + " " + message);
    }
}
