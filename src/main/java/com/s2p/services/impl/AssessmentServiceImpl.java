package com.s2p.services.impl;

import com.s2p.dto.AssessmentDTO;
import com.s2p.model.Assessment;
import com.s2p.model.Course;
import com.s2p.model.QuestionPaper;
import com.s2p.model.Topic;
import com.s2p.repository.AssessmentRepository;
import com.s2p.repository.CourseRepository;
import com.s2p.repository.QuestionPaperRepository;
import com.s2p.repository.TopicRepository;
import com.s2p.services.AssessmentService;
import com.s2p.util.AssessmentUtility;
import com.s2p.util.CourseUtility;
import com.s2p.util.QuestionPaperUtility;
import com.s2p.util.TopicUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AssessmentServiceImpl implements AssessmentService {

    private final AssessmentRepository assessmentRepository;
    private final CourseRepository courseRepository;
    private final TopicRepository topicRepository;
    private final QuestionPaperRepository questionPaperRepository;
    private final AssessmentUtility assessmentUtility;


    @Override
    public AssessmentDTO createAssessment(AssessmentDTO assessmentDTO) {

        Assessment assessment = assessmentUtility.toAssessmentEntity(assessmentDTO);

        Course course = courseRepository.findByCourseName(
                assessmentDTO.getCourseDto().getCourseName()
        ).orElseThrow(() -> new RuntimeException("Course not found"));

        Topic topic = topicRepository.findByTopicName(
                assessmentDTO.getTopicDTO().getTopicName()
        ).orElseThrow(() -> new RuntimeException("Topic not found"));

        QuestionPaper questionPaper = questionPaperRepository.findByTitle(
                assessmentDTO.getQuestionPaperDto().getTitle()
        ).orElseThrow(() -> new RuntimeException("Question Paper not found"));


        assessment.setCourse(course);
        assessment.setTopic(topic);
        assessment.setQuestionPaper(questionPaper);

        Assessment savedAssessment = assessmentRepository.save(assessment);

        return assessmentUtility.toAssessmentDto(savedAssessment);
    }

    @Override
    public AssessmentDTO getAssessmentByTitle(String title) {
        // fetch assessment by title
        Optional<Assessment> optionalAssessment = assessmentRepository.findByTitle(title);
        if (!optionalAssessment.isPresent()) {
            throw new RuntimeException("Assessment not found with title: " + title);
        }
        Assessment assessment = optionalAssessment.get();

        // convert to DTO and return
        return assessmentUtility.toAssessmentDto(assessment);
    }


    @Override
    public List<AssessmentDTO> getAllAssessments() {
        List<Assessment> assessments = assessmentRepository.findAll();
        List<AssessmentDTO> assessmentDTOList = new ArrayList<AssessmentDTO>();

        for (Assessment assessment : assessments) {
            assessmentDTOList.add(assessmentUtility.toAssessmentDto(assessment));
        }

        return assessmentDTOList;
    }

    @Override
    public AssessmentDTO updateAssessment(String assessmentTitle, AssessmentDTO assessmentDTO) {

        // fetch Assessment by title
        Optional<Assessment> optionalAssessment = assessmentRepository.findByTitle(assessmentTitle);
        if (!optionalAssessment.isPresent()) {
            throw new RuntimeException("Assessment not found with title: " + assessmentTitle);
        }
        Assessment assessment = optionalAssessment.get();

        // update basic fields
        assessment.setTitle(assessmentDTO.getTitle());
        assessment.setDescription(assessmentDTO.getDescription());
        assessment.setTotalMarks(assessmentDTO.getTotalMarks());
        assessment.setPassingMarks(assessmentDTO.getPassingMarks());

        // update Course if provided
        if (assessmentDTO.getCourseDto() != null && assessmentDTO.getCourseDto().getCourseName() != null) {
            Optional<Course> courseOpt = courseRepository.findByCourseName(assessmentDTO.getCourseDto().getCourseName());
            if (!courseOpt.isPresent()) {
                throw new RuntimeException("Course not found with name: " + assessmentDTO.getCourseDto().getCourseName());
            }
            assessment.setCourse(courseOpt.get());
        }

        // update Topic if provided
        if (assessmentDTO.getTopicDTO() != null && assessmentDTO.getTopicDTO().getTopicName() != null) {
            Optional<Topic> topicOpt = topicRepository.findByTopicName(assessmentDTO.getTopicDTO().getTopicName());
            if (!topicOpt.isPresent()) {
                throw new RuntimeException("Topic not found with name: " + assessmentDTO.getTopicDTO().getTopicName());
            }
            assessment.setTopic(topicOpt.get());
        }

        // update QuestionPaper if provided
        if (assessmentDTO.getQuestionPaperDto() != null && assessmentDTO.getQuestionPaperDto().getTitle() != null) {
            Optional<QuestionPaper> qpOpt = questionPaperRepository.findByTitle(assessmentDTO.getQuestionPaperDto().getTitle());
            if (!qpOpt.isPresent()) {
                throw new RuntimeException("QuestionPaper not found with title: " + assessmentDTO.getQuestionPaperDto().getTitle());
            }
            assessment.setQuestionPaper(qpOpt.get());
        }

        Assessment updatedAssessment = assessmentRepository.save(assessment);

        return assessmentUtility.toAssessmentDto(updatedAssessment);
    }

    @Override
    public void deleteAssessment(String title) {
        Assessment existing = assessmentRepository.findByTitle(title)
                .orElseThrow(() -> new RuntimeException("Assessment not found"));

        assessmentRepository.delete(existing);
    }

    public List<AssessmentDTO> getAssessmentsByTopicName(String topicName) {
        List<Assessment> assessmentList = assessmentRepository.findByTopic_TopicName(topicName);
        List<AssessmentDTO> assessmentDtoList = new ArrayList<AssessmentDTO>();

        for (Assessment assessment : assessmentList) {
            AssessmentDTO dto = assessmentUtility.toAssessmentDto(assessment);
            assessmentDtoList.add(dto);
        }

        return assessmentDtoList;
    }

}
