package com.QuoraApp.QuoraApp.controller;

import com.QuoraApp.QuoraApp.dto.QuestionDto;
import com.QuoraApp.QuoraApp.dto.QuestionResponseDto;
import com.QuoraApp.QuoraApp.service.IQuestionService;
import com.QuoraApp.QuoraApp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    private final IQuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping
    public Mono<QuestionResponseDto> createQuestion(QuestionDto questionDto) {
        return questionService.createQuestion(questionDto);
    }
}
