package com.QuoraApp.QuoraApp.service;

import com.QuoraApp.QuoraApp.dto.QuestionDto;
import com.QuoraApp.QuoraApp.dto.QuestionResponseDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IQuestionService {

    Mono<QuestionResponseDto> createQuestion(QuestionDto questionDto);

    Mono<QuestionResponseDto> getQuestionById(String id);

    Flux<QuestionResponseDto> getQuestions(int offset, int limit);
}
