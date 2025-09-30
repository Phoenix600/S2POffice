package com.s2p.services.impl;

import com.s2p.dto.TopicDTO;
import com.s2p.model.Course;
import com.s2p.model.Topic;
import com.s2p.repository.CourseRepository;
import com.s2p.repository.TopicRepository;
import com.s2p.services.ITopicService;
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
public class TopicServiceImpl implements ITopicService {

    private final TopicRepository topicRepository;
    private final CourseRepository courseRepository;
    private final TopicUtility topicUtility;

    @Override
    @Transactional
    public TopicDTO createTopic(TopicDTO dto) {
        // Fetch Course via courseName
        Optional<Course> courseOptional = courseRepository.findByCourseName(dto.getCourseDto().getCourseName());
        if (!courseOptional.isPresent()) {
            throw new RuntimeException("Course not found with name: " + dto.getCourseDto().getCourseName());
        }

        Topic topic = topicUtility.toTopicEntity(dto);
        topic.setCourse(courseOptional.get());

        Topic savedTopic = topicRepository.save(topic);

        return topicUtility.toTopicDto(savedTopic);
    }

    @Override
    public TopicDTO getTopicByName(String topicName) {
        // Fetch Topic via topicName
        Optional<Topic> optionalTopic = topicRepository.findByTopicName((topicName));
        if (!optionalTopic.isPresent()) {
            throw new RuntimeException("Topic not found with name: " + topicName);
        }

        // Map entity to DTO
        return topicUtility.toTopicDto(optionalTopic.get());
    }

    @Override
    public List<TopicDTO> getAllTopics() {
        // Fetch all Topic entities
        List<Topic> topics = topicRepository.findAll();

        // Map each Topic entity to TopicDTO
        List<TopicDTO> topicDTOs = new ArrayList<>();
        for (Topic topic : topics) {
            topicDTOs.add(topicUtility.toTopicDto(topic));
        }

        return topicDTOs;
    }

    @Override
    @Transactional
    public TopicDTO updateTopicByTopicName(String topicName, TopicDTO dto) {
        // Fetch existing Topic via topicName
        Optional<Topic> optionalTopic = topicRepository.findByTopicName((topicName));
        if (!optionalTopic.isPresent()) {
            throw new RuntimeException("Topic not found with name: " + topicName);
        }

        Topic existingTopic = optionalTopic.get();

        // Update fields
        existingTopic.setTopicName(dto.getTopicName());

        // Fetch Course via courseName if provided
        if (dto.getCourseDto() != null) {
            Optional<Course> courseOptional = courseRepository.findByCourseName(dto.getCourseDto().getCourseName());
            if (!courseOptional.isPresent()) {
                throw new RuntimeException("Course not found with name: " + dto.getCourseDto().getCourseName());
            }
            existingTopic.setCourse(courseOptional.get());
        }

        // Save updated entity
        Topic updatedTopic = topicRepository.save(existingTopic);

        // Map back to DTO
        return topicUtility.toTopicDto(updatedTopic);
    }


    @Override
    public void deleteTopic(String topicName) {
        Optional<Topic> topicOpt = topicRepository.findByTopicName((topicName));
        if (!topicOpt.isPresent()) {
            throw new RuntimeException("Topic not found with name: " + topicName);
        }

        topicRepository.delete(topicOpt.get());
    }
}
