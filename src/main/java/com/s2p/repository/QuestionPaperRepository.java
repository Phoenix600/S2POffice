package com.s2p.repository;

import com.s2p.model.QuestionPaper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface QuestionPaperRepository extends JpaRepository<QuestionPaper, UUID> {

    List<QuestionPaper> findByTopicName(String topicName);

    Optional<QuestionPaper> findByTitle(String title);
}
