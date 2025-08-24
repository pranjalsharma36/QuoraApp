package com.QuoraApp.QuoraApp.controller;

import com.QuoraApp.QuoraApp.dto.QuestionDto;
import com.QuoraApp.QuoraApp.dto.QuestionResponseDto;
import com.QuoraApp.QuoraApp.service.IQuestionService;
import com.QuoraApp.QuoraApp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
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

    @GetMapping("/{id}")
    public Mono<QuestionResponseDto> getQuestion(@PathVariable String id) {
        return questionService.getQuestionById(id);
    }

    @GetMapping
    public Flux<QuestionResponseDto> getQuestions(@RequestParam(defaultValue = "0") int offset,
                                                  @RequestParam(defaultValue = "10") int limit){
        return questionService.getQuestions(offset, limit);
    }
}
