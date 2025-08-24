package com.QuoraApp.QuoraApp.consumer;

import com.QuoraApp.QuoraApp.config.KafkaConsumerConfig;
import com.QuoraApp.QuoraApp.config.KafkaTopicConfig;
import com.QuoraApp.QuoraApp.event.ViewCountEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TargetEventConsumer {

    @KafkaListener(topics = KafkaTopicConfig.TOPIC_NAME, groupId = KafkaConsumerConfig.GROUP_ID)
    public void consumeMessage(ViewCountEvent viewCountEvent) {
        System.out.println("Received message: " + viewCountEvent);
    }
}
