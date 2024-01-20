package com.quizapp.service;

import com.quizapp.entity.Questions;
import com.quizapp.repository.QuestionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class QuestionsService {

    @Autowired
    private QuestionsRepository questionsRepository;

    public ResponseEntity<List<Questions>> getAllQuestions(){
       try {
           return new ResponseEntity<>(questionsRepository.findAll(), HttpStatus.OK);
       }catch (Exception e){
           e.printStackTrace();
       }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Questions>> getQuestionsByCategory(String category){
        try {
            return new ResponseEntity<>(questionsRepository.getQuestionsByCategory(category),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Questions> addQuestion(Questions questions){
        try {
            return new ResponseEntity<>(questionsRepository.save(questions), HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
        }
      return  null;
    }

    public String delete(Long id){
         questionsRepository.deleteById(id);
         return "Deleted successfully!!";
    }

}
