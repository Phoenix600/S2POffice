package com.s2p.services.impl;

import com.s2p.dto.QuestionPaperDTO;
import com.s2p.model.Question;
import com.s2p.model.QuestionPaper;
import com.s2p.model.Topic;
import com.s2p.repository.QuestionPaperRepository;
import com.s2p.repository.QuestionRepository;
import com.s2p.repository.TopicRepository;
import com.s2p.services.IQuestionPaperService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
@Transactional
public class QuestionPaperServiceImpl implements IQuestionPaperService {

    private final QuestionPaperRepository questionPaperRepository;
    private final TopicRepository topicRepository;
    private final QuestionRepository questionRepository;

    public QuestionPaperServiceImpl(QuestionPaperRepository questionPaperRepository,
                                    TopicRepository topicRepository,
                                    QuestionRepository questionRepository) {
        this.questionPaperRepository = questionPaperRepository;
        this.topicRepository = topicRepository;
        this.questionRepository = questionRepository;
    }

    @Override
    public QuestionPaperDTO createQuestionPaper(QuestionPaperDTO questionPaperDTO) {
        Optional<Topic> topicOpt = topicRepository.findById(questionPaperDTO.getTopicId());
        if (!topicOpt.isPresent()) {
            throw new RuntimeException("Topic not found with id: " + questionPaperDTO.getTopicId());
        }

        QuestionPaper qp = new QuestionPaper();
        qp.setTitle(questionPaperDTO.getTitle());
        qp.setTopic(topicOpt.get());

        Set<Question> questions = new HashSet<>();
        if (questionPaperDTO.getQuestionIds() != null) {
            for (UUID qId : questionPaperDTO.getQuestionIds()) {
                Optional<Question> qOpt = questionRepository.findById(qId);
                if (qOpt.isPresent()) {
                    questions.add(qOpt.get());
                }
            }
        }
        qp.setQuestions(questions);

        QuestionPaper saved = questionPaperRepository.save(qp);

        QuestionPaperDTO dto = new QuestionPaperDTO();
        dto.setQuestionPaperId(saved.getQuestionPaperId());
        dto.setTitle(saved.getTitle());
        dto.setTopicId(saved.getTopic().getTopicId());

        Set<UUID> qIds = new HashSet<>();
        for (Question q : saved.getQuestions()) {
            qIds.add(q.getQuestionId());
        }
        dto.setQuestionIds(qIds);

        return dto;
    }

    @Override
    public QuestionPaperDTO getQuestionPaperByTitle(String title) {
        Optional<QuestionPaper> qpOpt = questionPaperRepository.findByTitle(title);
        if (!qpOpt.isPresent()) {
            throw new RuntimeException("QuestionPaper not found with title: " + title);
        }

        QuestionPaper qp = qpOpt.get();
        QuestionPaperDTO dto = new QuestionPaperDTO();
        dto.setQuestionPaperId(qp.getQuestionPaperId());
        dto.setTitle(qp.getTitle());
        dto.setTopicId(qp.getTopic().getTopicId());

        Set<UUID> qIds = new HashSet<>();
        for (Question q : qp.getQuestions()) {
            qIds.add(q.getQuestionId());
        }
        dto.setQuestionIds(qIds);

        return dto;
    }

    @Override
    public List<QuestionPaperDTO> getAllQuestionPapers() {
        List<QuestionPaper> allQPs = questionPaperRepository.findAll();
        List<QuestionPaperDTO> result = new ArrayList<>();

        for (QuestionPaper qp : allQPs) {
            QuestionPaperDTO dto = new QuestionPaperDTO();
            dto.setQuestionPaperId(qp.getQuestionPaperId());
            dto.setTitle(qp.getTitle());
            dto.setTopicId(qp.getTopic().getTopicId());

            Set<UUID> qIds = new HashSet<>();
            for (Question q : qp.getQuestions()) {
                qIds.add(q.getQuestionId());
            }
            dto.setQuestionIds(qIds);

            result.add(dto);
        }

        return result;
    }

    @Override
    public QuestionPaperDTO updateQuestionPaper(String title, QuestionPaperDTO questionPaperDTO) {
        Optional<QuestionPaper> qpOpt = questionPaperRepository.findByTitle(title);
        if (!qpOpt.isPresent()) {
            throw new RuntimeException("QuestionPaper not found with title: " + title);
        }

        QuestionPaper existing = qpOpt.get();

        if (questionPaperDTO.getTitle() != null) existing.setTitle(questionPaperDTO.getTitle());
        if (questionPaperDTO.getTopicId() != null) {
            Optional<Topic> topicOpt = topicRepository.findById(questionPaperDTO.getTopicId());
            if (!topicOpt.isPresent()) {
                throw new RuntimeException("Topic not found with id: " + questionPaperDTO.getTopicId());
            }
            existing.setTopic(topicOpt.get());
        }

        if (questionPaperDTO.getQuestionIds() != null) {
            Set<Question> questions = new HashSet<>();
            for (UUID qId : questionPaperDTO.getQuestionIds()) {
                Optional<Question> qOpt = questionRepository.findById(qId);
                if (qOpt.isPresent()) {
                    questions.add(qOpt.get());
                }
            }
            existing.setQuestions(questions);
        }

        QuestionPaper updated = questionPaperRepository.save(existing);

        QuestionPaperDTO dto = new QuestionPaperDTO();
        dto.setQuestionPaperId(updated.getQuestionPaperId());
        dto.setTitle(updated.getTitle());
        dto.setTopicId(updated.getTopic().getTopicId());

        Set<UUID> qIds = new HashSet<>();
        for (Question q : updated.getQuestions()) {
            qIds.add(q.getQuestionId());
        }
        dto.setQuestionIds(qIds);

        return dto;
    }

    @Override
    public void deleteQuestionPaper(String title) {
        Optional<QuestionPaper> qpOpt = questionPaperRepository.findByTitle(title);
        if (!qpOpt.isPresent()) {
            throw new RuntimeException("QuestionPaper not found with title: " + title);
        }

        questionPaperRepository.delete(qpOpt.get());
    }
}
