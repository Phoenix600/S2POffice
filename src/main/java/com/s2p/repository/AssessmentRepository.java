package com.s2p.repository;

import com.s2p.model.Assessment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AssessmentRepository extends JpaRepository<Assessment, UUID> {

    List<Assessment> findByCourse_CourseName(String courseName);

    List<Assessment> findByTopic_TopicName(String topicName);

    Optional<Assessment> findByTitle(String title);
}
