package com.quizapp.service;

import com.quizapp.entity.QuestionWrapper;
import com.quizapp.entity.Questions;
import com.quizapp.entity.Quiz;
import com.quizapp.entity.Response;
import com.quizapp.repository.QuestionsRepository;
import com.quizapp.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuestionsRepository questionsRepository;

    public ResponseEntity<Quiz> createQuiz(String category, int numQ, String title){

        List<Questions> questions = questionsRepository.findRandomQuestionsByCategory(category,numQ);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);

        return new ResponseEntity<>(quizRepository.save(quiz), HttpStatus.CREATED);
    }

    public ResponseEntity <List<QuestionWrapper>> getQuizQuestions(Long id){
        Optional<Quiz> quiz = quizRepository.findById(id);
        List<Questions> questionsFromDB = quiz.get().getQuestions();
        List<QuestionWrapper> questionsForUser = new ArrayList<>();
        for (Questions q : questionsFromDB){
            QuestionWrapper questionWrapper= new QuestionWrapper(q.getId(), q.getQuestion_title(), q.getOption_1(), q.getOption_2(), q.getOption_3());
            questionsForUser.add(questionWrapper);
        }
        return new ResponseEntity<>(questionsForUser, HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(Long id, List<Response> responses) {
        Quiz quiz = quizRepository.findById(id).get();
        List<Questions> questions = quiz.getQuestions();
        int right = 0;
        int i = 0;
        for(Response response : responses){
            if(response.getResponse().equals(questions.get(i).getRight_answer()))
                right++;

            i++;
        }
        return new ResponseEntity<>(right, HttpStatus.OK);
    }
}
