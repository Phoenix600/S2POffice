package com.s2p.services.impl;

import com.s2p.dto.TopicDTO;
import com.s2p.model.Course;
import com.s2p.model.Topic;
import com.s2p.repository.CourseRepository;
import com.s2p.repository.TopicRepository;
import com.s2p.services.ITopicService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TopicServiceImpl implements ITopicService {

    private final TopicRepository topicRepository;
    private final CourseRepository courseRepository;

    public TopicServiceImpl(TopicRepository topicRepository, CourseRepository courseRepository) {
        this.topicRepository = topicRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public TopicDTO createTopic(TopicDTO topicDTO) {
        Optional<Course> courseOpt = courseRepository.findById(topicDTO.getCourseId());
        if (!courseOpt.isPresent()) {
            throw new RuntimeException("Course not found with id: " + topicDTO.getCourseId());
        }

        Topic topic = new Topic();
        topic.setTopicName(topicDTO.getTopicName());
        topic.setCourse(courseOpt.get());

        Topic saved = topicRepository.save(topic);

        TopicDTO dto = new TopicDTO();
        dto.setTopicId(saved.getTopicId());
        dto.setTopicName(saved.getTopicName());
        dto.setCourseId(saved.getCourse().getCourseId());

        return dto;
    }

    @Override
    public TopicDTO getTopicByName(String topicName) {
        Optional<Topic> topicOpt = topicRepository.findByTopicName(topicName);
        if (!topicOpt.isPresent()) {
            throw new RuntimeException("Topic not found with name: " + topicName);
        }

        Topic topic = topicOpt.get();
        TopicDTO dto = new TopicDTO();
        dto.setTopicId(topic.getTopicId());
        dto.setTopicName(topic.getTopicName());
        dto.setCourseId(topic.getCourse().getCourseId());

        return dto;
    }

    @Override
    public List<TopicDTO> getAllTopics() {
        List<Topic> allTopics = topicRepository.findAll();
        List<TopicDTO> result = new ArrayList<>();

        for (Topic t : allTopics) {
            TopicDTO dto = new TopicDTO();
            dto.setTopicId(t.getTopicId());
            dto.setTopicName(t.getTopicName());
            dto.setCourseId(t.getCourse().getCourseId());
            result.add(dto);
        }

        return result;
    }

    @Override
    public TopicDTO updateTopic(String topicName, TopicDTO topicDTO) {
        Optional<Topic> topicOpt = topicRepository.findByTopicName(topicName);
        if (!topicOpt.isPresent()) {
            throw new RuntimeException("Topic not found with name: " + topicName);
        }

        Topic existing = topicOpt.get();

        if (topicDTO.getTopicName() != null) {
            existing.setTopicName(topicDTO.getTopicName());
        }

        if (topicDTO.getCourseId() != null) {
            Optional<Course> courseOpt = courseRepository.findById(topicDTO.getCourseId());
            if (!courseOpt.isPresent()) {
                throw new RuntimeException("Course not found with id: " + topicDTO.getCourseId());
            }
            existing.setCourse(courseOpt.get());
        }

        Topic updated = topicRepository.save(existing);

        TopicDTO dto = new TopicDTO();
        dto.setTopicId(updated.getTopicId());
        dto.setTopicName(updated.getTopicName());
        dto.setCourseId(updated.getCourse().getCourseId());

        return dto;
    }

    @Override
    public void deleteTopic(String topicName) {
        Optional<Topic> topicOpt = topicRepository.findByTopicName(topicName);
        if (!topicOpt.isPresent()) {
            throw new RuntimeException("Topic not found with name: " + topicName);
        }

        topicRepository.delete(topicOpt.get());
    }
}
