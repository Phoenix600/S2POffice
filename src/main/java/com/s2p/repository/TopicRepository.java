package com.s2p.repository;

import com.s2p.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TopicRepository extends JpaRepository<Topic, UUID> {

    List<Topic> findByCourseName(String courseName);

    Optional<Topic> findByTopicName(String topicName);

    boolean existsByTopicName(String topicName);
}
