package com.s2p.junitTesting;

import com.s2p.dto.QuestionDTO;
import com.s2p.services.IQuestionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class QuestionServiceImplJunit {

    @Autowired
    IQuestionService questionService;

    @Test
    void createQuestion() {

    }

    @Test
    void getQuestionByText() {
    }

    @Test
    void getAllQuestions() {
    }

    @Test
    void updateQuestion() {
    }

    @Test
    void deleteQuestion() {
    }
}