package com.QuoraApp.QuoraApp.producer;

import com.QuoraApp.QuoraApp.config.KafkaTopicConfig;
import com.QuoraApp.QuoraApp.event.ViewCountEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TargetEventProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendMessage(ViewCountEvent message, String id) {
        kafkaTemplate.send(KafkaTopicConfig.TOPIC_NAME, id, message)
                .whenComplete((result, ex) -> {
                    if (ex != null) {
                        System.err.println("Failed to send message: " + ex.getMessage());
                    } else {
                        System.out.println("Message sent successfully to topic: " + KafkaTopicConfig.TOPIC_NAME);
                    }
                });
    }
}
