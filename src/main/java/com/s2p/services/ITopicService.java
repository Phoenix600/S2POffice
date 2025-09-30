package com.s2p.services;

import com.s2p.dto.TopicDTO;
import java.util.List;

public interface ITopicService {
    public abstract TopicDTO createTopicByCourseName(String courseName, TopicDTO topicDTO);
    public abstract TopicDTO getTopicByName(String topicName);
    public abstract List<TopicDTO> getAllTopics();
    public abstract TopicDTO updateTopicByTopicName(String topicName, TopicDTO dto);
    public abstract void deleteTopic(String topicName);
}
