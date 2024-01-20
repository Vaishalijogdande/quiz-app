package com.quizapp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class QuestionWrapper {
    private Long id;
    private String question_title;
    private String option_1;
    private String option_2;
    private String option_3;

    public QuestionWrapper(Long id, String question_title, String option_1, String option_2, String option_3) {
        this.id = id;
        this.question_title = question_title;
        this.option_1 = option_1;
        this.option_2 = option_2;
        this.option_3 = option_3;
    }
}
