package com.s2p.services.impl;

import com.s2p.dto.TopicDTO;
import com.s2p.services.ITopicService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicServiceImpl implements ITopicService {
    @Override
    public TopicDTO createTopic(TopicDTO topicDTO) {
        return null;
    }

    @Override
    public TopicDTO getTopicByName(String topicName) {
        return null;
    }

    @Override
    public List<TopicDTO> getAllTopics() {
        return List.of();
    }

    @Override
    public TopicDTO updateTopic(String topicName, TopicDTO topicDTO) {
        return null;
    }

    @Override
    public void deleteTopic(String topicName) {

    }
}
