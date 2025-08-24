package com.QuoraApp.QuoraApp.controller;

import com.QuoraApp.QuoraApp.model.Answer;
import com.QuoraApp.QuoraApp.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/answers")
public class AnswerController {
    private final AnswerService answerService;

    @Autowired
    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @PostMapping
    public Mono<Answer> createAnswer(@RequestBody Answer answer) {
        return answerService.createAnswer(answer);
    }

    @GetMapping("/{id}")
    public Mono<Answer> getAnswerById(@PathVariable String id) {
        return answerService.getAnswerById(id);
    }

    @GetMapping
    public Flux<Answer> getAllAnswers() {
        return answerService.getAllAnswers();
    }

    @PutMapping("/{id}")
    public Mono<Answer> updateAnswer(@PathVariable String id, @RequestBody Answer answer) {
        return answerService.updateAnswer(id, answer);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteAnswer(@PathVariable String id) {
        return answerService.deleteAnswer(id);
    }
}

