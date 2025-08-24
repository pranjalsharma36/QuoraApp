package com.QuoraApp.QuoraApp.repository;

import com.QuoraApp.QuoraApp.model.Answer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends ReactiveMongoRepository<Answer, String> {
    // Custom query methods can be added here if needed
}

