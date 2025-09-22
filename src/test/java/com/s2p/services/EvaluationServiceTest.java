package com.s2p.services;

import com.s2p.dto.EvaluationRequestDTO;
import com.s2p.dto.EvaluationResultDTO;
import com.s2p.dto.ScoreCardDTO;
import com.s2p.dto.StudentInformationDto;
import com.s2p.services.impl.ScoreCardServiceImpl;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.junit5.AllureJunit5;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(AllureJunit5.class)
@Epic("Master Data Module")   // High-level grouping
@Feature("Country Service")   // Feature under testing
@Slf4j
class EvaluationServiceTest {

    @Mock
    private EvaluationService evaluationService;

    @Mock
    private IStudentInformationService studentService;

    @InjectMocks
    private ScoreCardServiceImpl scoreCardService;

    private EvaluationRequestDTO request;
    private EvaluationResultDTO evalResult;
    private StudentInformationDto studentDto;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // Mock request
        request = new EvaluationRequestDTO();
        request.setStudentEmail("student@example.com");
        request.setTitle("Java Basics Test");
        request.setStudentAnswers(Arrays.asList("A", "B", "C"));

        // Mock evaluation result
        evalResult = new EvaluationResultDTO();
        evalResult.setStudentEmail("student@example.com");
        evalResult.setQuestionPaperCode("Java Basics Test");
        evalResult.setTotalQuestions(3);
        evalResult.setCorrectAnswers(2);
        evalResult.setAnswerEvaluation(Arrays.asList(true, false, true));

        // Mock student info
        studentDto = new StudentInformationDto();
        studentDto.setFirstName("Kanika");
        studentDto.setLastName("Agrawal");
        studentDto.setEmail("student@example.com");
        studentDto.setStudentInformationId(UUID.randomUUID());


    }


    @Test
    public void testGenerateScoreCard() {
        // Mock behavior
        when(evaluationService.evaluate(request)).thenReturn(evalResult);
        when(studentService.getStudentByEmail("student@example.com")).thenReturn(Optional.of(studentDto));

        // Call service
        ScoreCardDTO scoreCard = scoreCardService.generateScoreCard(request);

        // Assertions
        assertEquals("Kanika Agrawal", scoreCard.getStudentName());
        assertEquals("student@example.com", scoreCard.getStudentEmail());
        assertEquals(3, scoreCard.getTotalQuestions());
        assertEquals(2, scoreCard.getCorrectAnswers());
        assertEquals(66.66666666666666, scoreCard.getScorePercentage(), 0.01);

        // Verify mocks
        verify(evaluationService, times(1)).evaluate(request);
        verify(studentService, times(1)).getStudentByEmail("student@example.com");
    }
}