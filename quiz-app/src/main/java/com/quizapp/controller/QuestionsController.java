package com.quizapp.controller;

import com.quizapp.entity.Questions;
import com.quizapp.service.QuestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questions")
public class QuestionsController {

    @Autowired
    private QuestionsService questionsService;

    @GetMapping("/getAllQuestions")
    public ResponseEntity<List<Questions>> getAllQuestions(){
        return questionsService.getAllQuestions();
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Questions>> getQuestionsByCategory(@PathVariable String category){
        return questionsService.getQuestionsByCategory(category);
    }

    @PostMapping("/add")
    public ResponseEntity<Questions> addQuestion(@RequestBody Questions questions){
        return questionsService.addQuestion(questions);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        questionsService.delete(id);
    }

}
