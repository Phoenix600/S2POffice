package com.s2p.util;

import com.s2p.dto.TopicDTO;
import com.s2p.model.Topic;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TopicUtility {
    public abstract Topic toTopicEntity(TopicDTO topicDTO);
    public abstract TopicDTO toTopicDto(Topic topic);
}
