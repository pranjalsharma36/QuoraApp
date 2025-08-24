package com.QuoraApp.QuoraApp.service;

import com.QuoraApp.QuoraApp.model.Answer;
import com.QuoraApp.QuoraApp.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AnswerService {
    private final AnswerRepository answerRepository;

    @Autowired
    public AnswerService(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    public Mono<Answer> createAnswer(Answer answer) {
        return answerRepository.save(answer);
    }

    public Mono<Answer> getAnswerById(String id) {
        return answerRepository.findById(id);
    }

    public Flux<Answer> getAllAnswers() {
        return answerRepository.findAll();
    }

    public Mono<Answer> updateAnswer(String id, Answer updatedAnswer) {
        return answerRepository.findById(id)
                .flatMap(existingAnswer -> {
                    existingAnswer.setContent(updatedAnswer.getContent());
                    existingAnswer.setUpdatedAt(updatedAnswer.getUpdatedAt());
                    // Optionally update other fields
                    return answerRepository.save(existingAnswer);
                });
    }

    public Mono<Void> deleteAnswer(String id) {
        return answerRepository.deleteById(id);
    }
}

