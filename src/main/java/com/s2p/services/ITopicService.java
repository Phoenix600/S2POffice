package com.s2p.services;

import com.s2p.dto.TopicDTO;
import java.util.List;

public interface ITopicService {
    public abstract TopicDTO createTopic(TopicDTO topicDTO);
    public abstract TopicDTO getTopicByName(String topicName);
    public abstract List<TopicDTO> getAllTopics();
    public abstract TopicDTO updateTopic(String topicName, TopicDTO topicDTO);
    public abstract void deleteTopic(String topicName);
}
