package com.QuoraApp.QuoraApp.service;

import com.QuoraApp.QuoraApp.adapter.QuestionAdapter;
import com.QuoraApp.QuoraApp.dto.QuestionDto;
import com.QuoraApp.QuoraApp.dto.QuestionResponseDto;
import com.QuoraApp.QuoraApp.model.Question;
import com.QuoraApp.QuoraApp.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
public class QuestionService implements IQuestionService{

    private final QuestionRepository questionRepository;

    @Autowired
    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public Mono<QuestionResponseDto> createQuestion(QuestionDto questionDto) {

        Question question = Question.builder()
                .title(questionDto.getTitle())
                .content(questionDto.getContent())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        return questionRepository.save(question)
                .map(QuestionAdapter::toQuestionResponseDto)
                .doOnSuccess(q -> {
                    // Additional logic can be added here if needed
                    // Acting as a subscriber to the Mono
                    System.out.println("Question created successfully: " + q.getTitle());
                })
                .doOnError(error -> {
                    // Handle error scenario => acting as subscriber
                    System.err.println("Error creating question: " + error.getMessage());
                });

    }
}
