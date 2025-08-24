package com.QuoraApp.QuoraApp.consumer;

import com.QuoraApp.QuoraApp.config.KafkaConsumerConfig;
import com.QuoraApp.QuoraApp.config.KafkaTopicConfig;
import com.QuoraApp.QuoraApp.event.ViewCountEvent;
import com.QuoraApp.QuoraApp.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TargetEventConsumer {

    private final QuestionRepository questionRepository;

    @KafkaListener(topics = KafkaTopicConfig.TOPIC_NAME, groupId = KafkaConsumerConfig.GROUP_ID)
    public void consumeMessage(ViewCountEvent viewCountEvent) {
        // Process the received message
        String targetId = viewCountEvent.getTargetId();
        int incrementBy = viewCountEvent.getIncrementBy();
        // Update the view count in the database
        questionRepository.findById(targetId)
                .flatMap(question -> {
                    question.setViewCount(question.getViewCount() == null ? 0 : question.getViewCount() + incrementBy);
                    return questionRepository.save(question);
                })
                .subscribe(updatedQuestion ->
                    System.out.println("Updated view count for question ID " + targetId + ": " + updatedQuestion.getViewCount()),
                    error ->
                    System.err.println("Error updating view count for question ID " + targetId + ": " + error.getMessage())
                );
        System.out.println("Received message: " + viewCountEvent);
    }
}
