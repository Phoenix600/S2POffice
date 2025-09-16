package com.s2p.repository;

import com.s2p.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface QuestionRepository extends JpaRepository<Question, UUID> {

    List<Question> findByQuestionPaper_Title(String questionPaperTitle);

    List<Question> findByQuestionTextContainingIgnoreCase(String keyword);

    Question findByQuestionText(String questionText);
}
