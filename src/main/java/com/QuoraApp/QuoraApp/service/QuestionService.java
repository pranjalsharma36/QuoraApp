package com.QuoraApp.QuoraApp.service;

import com.QuoraApp.QuoraApp.adapter.QuestionAdapter;
import com.QuoraApp.QuoraApp.dto.QuestionDto;
import com.QuoraApp.QuoraApp.dto.QuestionResponseDto;
import com.QuoraApp.QuoraApp.enums.TargetType;
import com.QuoraApp.QuoraApp.event.ViewCountEvent;
import com.QuoraApp.QuoraApp.model.Question;
import com.QuoraApp.QuoraApp.producer.TargetEventProducer;
import com.QuoraApp.QuoraApp.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class QuestionService implements IQuestionService{

    private final QuestionRepository questionRepository;
    private final TargetEventProducer targetEventProducer;


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

    @Override
    public Mono<QuestionResponseDto> getQuestionById(String id) {
        Mono<QuestionResponseDto> questionResponseDtoMono =  questionRepository.findById(id)
                .map(QuestionAdapter::toQuestionResponseDto)
                .doOnSuccess(q -> {
                    System.out.println("Question retrieved successfully: " + q.getTitle());
                    // Produce a Kafka event for view count increment
                    ViewCountEvent viewCountEvent = new ViewCountEvent(id, TargetType.QUESTION, LocalDateTime.now(), 1);
                    targetEventProducer.sendMessage(viewCountEvent, id);
                })
                .doOnError(error -> {
                    System.err.println("Error retrieving question: " + error.getMessage());
                });

        return questionResponseDtoMono;
    }

    @Override
    public Flux<QuestionResponseDto> getQuestions(int offset, int limit) {
        return questionRepository.findAllBy(PageRequest.of(offset, limit))
                .map(QuestionAdapter::toQuestionResponseDto)
                .doOnError(error -> {
                    System.err.println("Error retrieving questions: " + error.getMessage());
                })
                .doOnComplete(() -> {
                    System.out.println("All questions retrieved successfully.");
                });
    }
}
