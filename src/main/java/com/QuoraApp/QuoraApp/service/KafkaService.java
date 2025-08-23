package com.QuoraApp.QuoraApp.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaService implements IKafkaService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final String topicName = "questionTopic";

    public KafkaService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void sendMessage(String message) {
        // Note : this returns us CompletableFuture<SendResult<String, String>>
        kafkaTemplate.send(topicName, message);
    }
}
