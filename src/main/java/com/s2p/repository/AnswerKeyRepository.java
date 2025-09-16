package com.s2p.repository;

import com.s2p.model.AnswerKey;
import com.s2p.model.QuestionPaper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AnswerKeyRepository extends JpaRepository<AnswerKey, UUID> {

    Optional<AnswerKey> findByQuestionPaper_Title(String questionPaperTitle);

    <T> ScopedValue<T> findByQuestionPaper(QuestionPaper questionPaper);
}
