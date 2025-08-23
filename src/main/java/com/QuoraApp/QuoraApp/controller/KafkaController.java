package com.QuoraApp.QuoraApp.controller;

import com.QuoraApp.QuoraApp.service.IKafkaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
public class KafkaController {

    private final IKafkaService kafkaService;

    public KafkaController(IKafkaService kafkaService) {
        this.kafkaService = kafkaService;
    }

    @PutMapping("/publish")
    public ResponseEntity<?> publishMessage(@RequestBody String message) {
        // Logic to publish message to Kafka topic
        kafkaService.sendMessage(message);
        return ResponseEntity.ok("Message published successfully");
    }
}
