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
import com.s2p.util.CourseUtility;
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
    private final CourseUtility courseUtility;

    public AssessmentServiceImpl(AssessmentRepository assessmentRepository,
                                 CourseRepository courseRepository,
                                 TopicRepository topicRepository,
                                 QuestionPaperRepository questionPaperRepository) {
        this.assessmentRepository = assessmentRepository;
        this.courseRepository = courseRepository;
        this.topicRepository = topicRepository;
        this.questionPaperRepository = questionPaperRepository;
    }

    @Override
    public AssessmentDTO createAssessment(AssessmentDTO assessmentDTO,String assessmentTitle)
    {
        Course course = courseRepository.findByCourseName(String.valueOf(assessmentDTO.getCourseDto()))
                .orElseThrow(() -> new RuntimeException("Course not found"));

        Topic topic = topicRepository.findByTopicName(String.valueOf(assessmentDTO.getTopicDTO()))
                .orElseThrow(() -> new RuntimeException("Topic not found"));

        QuestionPaper questionPaper = questionPaperRepository.findByTitle(String.valueOf(assessmentDTO.getQuestionPaperDto()))
                .orElseThrow(() -> new RuntimeException("QuestionPaper not found"));

        Assessment assessment = new Assessment();
        assessment.setTitle(assessmentDTO.getTitle());
        assessment.setDescription(assessmentDTO.getDescription());
        assessment.setPassingMarks(assessmentDTO.getPassingMarks());
        assessment.setTotalMarks(assessmentDTO.getTotalMarks());
        assessment.setCourse(assessmentDTO.getCourseDto());


        Assessment assessment = new Assessment();
        assessment.setTitle(assessmentDTO.getTitle());
        assessment.setDescription(assessmentDTO.getDescription());
        assessment.setTotalMarks(assessmentDTO.getTotalMarks());
        assessment.setPassingMarks(assessmentDTO.getPassingMarks());
        assessment.setCourse(course);
        assessment.setTopic(topic);
        assessment.setQuestionPaper(questionPaper);

        Assessment saved = assessmentRepository.save(assessment);

        AssessmentDTO resultDTO = new AssessmentDTO();
        resultDTO.setAssessmentId(saved.getAssessmentId());
        resultDTO.setTitle(saved.getTitle());
        resultDTO.setDescription(saved.getDescription());
        resultDTO.setTotalMarks(saved.getTotalMarks());
        resultDTO.setPassingMarks(saved.getPassingMarks());
        resultDTO.setCourseId(saved.getCourse().getCourseId());
        resultDTO.setTopicId(saved.getTopic().getTopicId());
        resultDTO.setQuestionPaperId(saved.getQuestionPaper().getQuestionPaperId());

        return resultDTO;
    }

    @Override
    public AssessmentDTO getAssessmentByTitle(String title) {
        Assessment assessment = assessmentRepository.findByTitle(title)
                .orElseThrow(() -> new RuntimeException("Assessment not found"));

        AssessmentDTO dto = new AssessmentDTO();
        dto.setAssessmentId(assessment.getAssessmentId());
        dto.setTitle(assessment.getTitle());
        dto.setDescription(assessment.getDescription());
        dto.setTotalMarks(assessment.getTotalMarks());
        dto.setPassingMarks(assessment.getPassingMarks());
        dto.setCourseId(assessment.getCourse().getCourseId());
        dto.setTopicId(assessment.getTopic().getTopicId());
        dto.setQuestionPaperId(assessment.getQuestionPaper().getQuestionPaperId());

        return dto;
    }

    @Override
    public List<AssessmentDTO> getAllAssessments() {
        List<Assessment> allAssessments = assessmentRepository.findAll();
        List<AssessmentDTO> result = new ArrayList<>();

        for (Assessment a : allAssessments) {
            AssessmentDTO dto = new AssessmentDTO();
            dto.setAssessmentId(a.getAssessmentId());
            dto.setTitle(a.getTitle());
            dto.setDescription(a.getDescription());
            dto.setTotalMarks(a.getTotalMarks());
            dto.setPassingMarks(a.getPassingMarks());
            dto.setCourseId(a.getCourse().getCourseId());
            dto.setTopicId(a.getTopic().getTopicId());
            dto.setQuestionPaperId(a.getQuestionPaper().getQuestionPaperId());
            result.add(dto);
        }

        return result;
    }

    @Override
    public AssessmentDTO updateAssessment(String title, AssessmentDTO assessmentDTO) {
        Assessment existing = assessmentRepository.findByTitle(title)
                .orElseThrow(() -> new RuntimeException("Assessment not found"));

        if (assessmentDTO.getTitle() != null) existing.setTitle(assessmentDTO.getTitle());
        if (assessmentDTO.getDescription() != null) existing.setDescription(assessmentDTO.getDescription());
        if (assessmentDTO.getTotalMarks() != null) existing.setTotalMarks(assessmentDTO.getTotalMarks());
        if (assessmentDTO.getPassingMarks() != null) existing.setPassingMarks(assessmentDTO.getPassingMarks());

        Assessment updated = assessmentRepository.save(existing);

        AssessmentDTO dto = new AssessmentDTO();
        dto.setAssessmentId(updated.getAssessmentId());
        dto.setTitle(updated.getTitle());
        dto.setDescription(updated.getDescription());
        dto.setTotalMarks(updated.getTotalMarks());
        dto.setPassingMarks(updated.getPassingMarks());
        dto.setCourseId(updated.getCourse().getCourseId());
        dto.setTopicId(updated.getTopic().getTopicId());
        dto.setQuestionPaperId(updated.getQuestionPaper().getQuestionPaperId());

        return dto;
    }

    @Override
    public void deleteAssessment(String title) {
        Assessment existing = assessmentRepository.findByTitle(title)
                .orElseThrow(() -> new RuntimeException("Assessment not found"));

        assessmentRepository.delete(existing);
    }

    @Override
    public List<AssessmentDTO> getAssessmentsByTopicName(String topicName) {
        Optional<Topic> topicOpt = topicRepository.findByTopicName(topicName);
        List<AssessmentDTO> result = new ArrayList<>();

        if (topicOpt.isPresent()) {
            Topic t = topicOpt.get();
            List<Assessment> assessments = assessmentRepository.findByTopic(t);

            for (Assessment a : assessments) {
                AssessmentDTO dto = new AssessmentDTO();
                dto.setAssessmentId(a.getAssessmentId());
                dto.setTitle(a.getTitle());
                dto.setDescription(a.getDescription());
                dto.setTotalMarks(a.getTotalMarks());
                dto.setPassingMarks(a.getPassingMarks());
                dto.setCourseId(a.getCourse().getCourseId());
                dto.setTopicId(a.getTopic().getTopicId());
                dto.setQuestionPaperId(a.getQuestionPaper().getQuestionPaperId());
                result.add(dto);
            }
        }

        return result;
    }

}
