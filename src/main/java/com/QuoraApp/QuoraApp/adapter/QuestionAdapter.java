package com.QuoraApp.QuoraApp.adapter;

import com.QuoraApp.QuoraApp.dto.QuestionResponseDto;
import com.QuoraApp.QuoraApp.model.Question;

public class QuestionAdapter {

    public static QuestionResponseDto toQuestionResponseDto(Question question) {
        return QuestionResponseDto.builder()
                .id(question.getId())
                .title(question.getTitle())
                .content(question.getContent())
                .createdAt(question.getCreatedAt())
                .viewCount(question.getViewCount())
                .build();
    }
}
