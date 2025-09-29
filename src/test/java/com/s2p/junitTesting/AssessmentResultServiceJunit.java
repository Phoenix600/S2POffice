package com.s2p.junitTesting;

import com.s2p.dto.*;
import com.s2p.model.*;
import com.s2p.services.AnswerKeyService;
import com.s2p.services.AssessmentResultService;
import com.s2p.services.AssessmentService;
import com.s2p.services.ITopicService;
import com.s2p.services.impl.CourseService;
import com.s2p.services.impl.QuestionPaperServiceImpl;
import com.s2p.services.impl.QuestionServiceImpl;
import com.s2p.services.impl.StudentInformationService;
import com.s2p.util.AssessmentResultUtility;
import com.s2p.util.AssessmentUtility;
import com.s2p.util.CourseUtility;
import com.s2p.util.StudentInformationUtility;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AssessmentResultServiceJunit {

    @Autowired
    private AssessmentResultService assessmentResultService;

    @Autowired
    StudentInformationUtility studentInformationUtility;

    @Autowired
    StudentInformationService studentInformationService;

    @Autowired
    AssessmentService assessmentService;

    @Autowired
    AssessmentResultUtility assessmentResultUtility;

    @Autowired
    AssessmentUtility assessmentUtility;

    @Autowired
    CourseService courseService;

    @Autowired
    CourseUtility courseUtility;

    @Autowired
    ITopicService topicService;

    @Autowired
    QuestionPaperServiceImpl questionPaperService;

    @Autowired
    QuestionServiceImpl questionService;

    @Autowired
    AnswerKeyService answerKeyService;

    @Transactional
    @Test
    void test_save_assessment_result_success() {

        // Create Course DTO And Save It
        CourseDto courseDto = new CourseDto();
        courseDto.setCourseName("Core Java mastery-123");
        courseDto.setDescription("This is some lame description");
        courseDto.setCourseDurationInMonths((byte)6);

        var savedCourse = courseService.createCourse(courseDto);

        // Create Topic Dto And Save It
        TopicDTO topicDTO = new TopicDTO();
        topicDTO.setTopicName("Introduction To Scanner Class");
        topicDTO.setCourseId(savedCourse.getCourseId());

        var savedTopic = topicService.createTopic(topicDTO);

        // Create and set Student Information
        StudentInformationDto studentInformationDto = new StudentInformationDto();
        studentInformationDto.setFirstName("Harsh1");
        studentInformationDto.setLastName("Diwathe1");
        studentInformationDto.setEmail("harsh.diwathe123@example.com");
        studentInformationDto.setCollegeName("JD College");
        studentInformationDto.setDepartName("Artificial Intelligence");
        studentInformationDto.setSemester("8th");
        studentInformationDto.setPassingYear("2025");

        var savedStudent =  studentInformationService.createStudent(studentInformationDto);

        savedStudent.setCourses(Set.of(courseUtility.toCourseEntity(courseDto)));

        // Create The Assessment
        AssessmentDTO assessment = new AssessmentDTO();

        assessment.setAssessmentId(UUID.randomUUID());
        assessment.setTitle("Java Basics Quiz");
        assessment.setDescription("Covers OOP and collections");
        assessment.setPassingMarks(40);
        assessment.setTotalMarks(100);
        assessment.setCourseId(savedCourse.getCourseId());
        assessment.setTopicId(savedTopic.getTopicId());

//        Set<QuestionDTO> questionDTOSet = new HashSet<>();
//
//        QuestionDTO q1 = new QuestionDTO();
//        q1.setQuestionText("What is the capital of India?");
//        q1.setOptionA("Mumbai");
//        q1.setOptionB("New Delhi");
//        q1.setOptionC("Kolkata");
//        q1.setOptionD("Chennai");
//
//        var savedQuestion1 = questionService.createQuestion(q1);
//        questionDTOSet.add(savedQuestion1);

        QuestionPaperDTO questionPaperDTO = new QuestionPaperDTO();
        questionPaperDTO.setTopicId(savedTopic.getTopicId());
        questionPaperDTO.setTitle(savedTopic.getTopicName());

        var savedQuestionPaper = questionPaperService.createQuestionPaper(questionPaperDTO);


        Map<Integer, String> answers = new HashMap<>();
        answers.put(1, "A");
        answers.put(2, "C");
        answers.put(3, "B");
        answers.put(4, "D");

        AnswerKeyDTO answerKeyDTO = new AnswerKeyDTO();
        answerKeyDTO.setAnswers(answers);
        answerKeyDTO.setQuestionPaperId(savedQuestionPaper.getQuestionPaperId());


        var savedAssessment =  assessmentService.createAssessment(assessment);

        AssessmentResultDTO dto = new AssessmentResultDTO();
        dto.setObtainedMarks(85);
        dto.setPassed(true);
        dto.setStudent(studentInformationUtility.toStudentInformationEntity(savedStudent));
        dto.setAssessment(assessmentUtility.toAssessmentEntity(savedAssessment));

        var savedAssessmentResult =  assessmentResultService.saveAssessmentResult(dto);

    }

    @Test
    void test_update_assessment_result_success() {
        String email = "harsh.diwathe@example.com";

        Assessment assessment = new Assessment();
        assessment.setTitle("Spring Boot Test");
        assessment.setDescription("Testing service methods");
        assessment.setPassingMarks(50);

        StudentInformation student = new StudentInformation();
        student.setEmail(email);

        // Save initial result
        AssessmentResultDTO dto = new AssessmentResultDTO();
        dto.setObtainedMarks(25);
        dto.setPassed(false);
        dto.setStudent(student);
        dto.setAssessment(assessment);
        assessmentResultService.saveAssessmentResult(dto);

        // Update marks
        dto.setObtainedMarks(60);
        dto.setPassed(true);

        AssessmentResultDTO updated = assessmentResultService.updateAssessmentResultByStudentEmail(email, dto);

        assertNotNull(updated);
        assertEquals(60, updated.getObtainedMarks());
        assertTrue(updated.getPassed());
    }

    @Test
    void test_remove_assessment_result_success() {
        String email = "harsh.diwathe@example.com";

        Assessment assessment = new Assessment();
        assessment.setTitle("React Quiz");
        assessment.setDescription("Frontend basics");
        assessment.setPassingMarks(30);

        StudentInformation student = new StudentInformation();
        student.setEmail(email);

        AssessmentResultDTO dto = new AssessmentResultDTO();
        dto.setObtainedMarks(40);
        dto.setPassed(true);
        dto.setStudent(student);
        dto.setAssessment(assessment);
        assessmentResultService.saveAssessmentResult(dto);

        // Act
        assertDoesNotThrow(() -> assessmentResultService.removeAssessmentResultByStudentEmail(email, dto));

        // Verify
        Optional<AssessmentResultDTO> found = assessmentResultService.findAssessmentResultByStudentEmail(email , dto);
        assertNull(found);
    }

    @Test
    void test_find_assessment_result_success() {
        String email = "harsh.diwathe@example.com";

        Assessment assessment = new Assessment();
        assessment.setTitle("Microservices Quiz");
        assessment.setDescription("Covers cloud topics");
        assessment.setPassingMarks(35);

        StudentInformation student = new StudentInformation();
        student.setEmail(email);

        AssessmentResultDTO dto = new AssessmentResultDTO();
        dto.setObtainedMarks(70);
        dto.setPassed(true);
        dto.setStudent(student);
        dto.setAssessment(assessment);
        assessmentResultService.saveAssessmentResult(dto);

        Optional<AssessmentResultDTO> found = assessmentResultService.findAssessmentResultByStudentEmail(email , dto);

        assertNotNull(found);
        assertEquals(email, found.get().getStudent().getEmail());
        assertEquals(70, found.get().getObtainedMarks());
    }

    @Test
    void test_find_all_results_by_student_email_success() {
        String email = "harsh.diwathe@example.com";

        Assessment assessment = new Assessment();
        assessment.setTitle("DSA Quiz");
        assessment.setDescription("Algorithms and complexity");
        assessment.setPassingMarks(45);

        StudentInformation student = new StudentInformation();
        student.setEmail(email);

        AssessmentResultDTO dto1 = new AssessmentResultDTO();
        dto1.setObtainedMarks(55);
        dto1.setPassed(true);
        dto1.setStudent(student);
        dto1.setAssessment(assessment);

        AssessmentResultDTO dto2 = new AssessmentResultDTO();
        dto2.setObtainedMarks(20);
        dto2.setPassed(false);
        dto2.setStudent(student);
        dto2.setAssessment(assessment);

        assessmentResultService.saveAssessmentResult(dto1);
        assessmentResultService.saveAssessmentResult(dto2);

        List<AssessmentResultDTO> results = assessmentResultService.findAllAssessmentResultsByStudentEmail(email);

        assertNotNull(results);
        assertFalse(results.isEmpty());
        assertTrue(results.stream().allMatch(r -> r.getStudent().getEmail().equals(email)));
    }

    @Test
    void test_find_all_results_success() {
        List<AssessmentResultDTO> results = assessmentResultService.findAllAssessmentResults();

        assertNotNull(results);
        assertFalse(results.isEmpty());
    }
}