package com.QuoraApp.QuoraApp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDto {

    @NotBlank(message = "Title cannot be blank")
    @Size(min= 10, max = 100, message = "Title must be less than 100 characters")
    private String title;

    @NotBlank(message = "Content cannot be blank")
    @Size(min= 10, max = 100, message = "Title must be less than 100 characters")
    private String content;
}
