package com.s2p.services.impl;

import com.s2p.dto.QuestionPaperDTO;
import com.s2p.model.Question;
import com.s2p.model.QuestionPaper;
import com.s2p.model.Topic;
import com.s2p.repository.QuestionPaperRepository;
import com.s2p.repository.QuestionRepository;
import com.s2p.repository.TopicRepository;
import com.s2p.services.IQuestionPaperService;
import com.s2p.util.QuestionPaperUtility;
import com.s2p.util.QuestionUtility;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class QuestionPaperServiceImpl implements IQuestionPaperService {

    private final QuestionPaperRepository questionPaperRepository;
    private final TopicRepository topicRepository;
    private final QuestionRepository questionRepository;
    private final QuestionPaperUtility questionPaperUtility;
    private final QuestionUtility  questionUtility;

    @Override
    @Transactional
    public QuestionPaperDTO createQuestionPaper(QuestionPaperDTO questionPaperDTO) {

        Optional<Topic> topicOptional = topicRepository.findByTopicName(questionPaperDTO.getTopicDTO().getTopicName());
        if (!topicOptional.isPresent()) {
            throw new RuntimeException("Topic not found with name: " + questionPaperDTO.getTopicDTO().getTopicName());
        }

        Topic topic = topicOptional.get();

        QuestionPaper questionPaper = questionPaperUtility.toQuestionPaperEntity(questionPaperDTO);
        questionPaper.setTopic(topic); // Set the actual Topic entity

        QuestionPaper savedQuestionPaper = questionPaperRepository.save(questionPaper);

        return questionPaperUtility.toQuestionPaperDto(savedQuestionPaper);
    }


    @Override
    public QuestionPaperDTO getQuestionPaperByTitle(String title) {

        Optional<QuestionPaper> questionPaperOptional = questionPaperRepository.findByTitle(title);

        if (!questionPaperOptional.isPresent()) {
            throw new RuntimeException("Question Paper not found with title: " + title);
        }

        QuestionPaper questionPaper = questionPaperOptional.get();
        return questionPaperUtility.toQuestionPaperDto(questionPaper);
    }



    @Override
    public List<QuestionPaperDTO> getAllQuestionPapers() {
        // Fetch all QuestionPaper entities
        List<QuestionPaper> questionPapers = questionPaperRepository.findAll();

        // Map each entity to DTO
        List<QuestionPaperDTO> dtoList = new ArrayList<>();
        for (QuestionPaper qp : questionPapers) {
            dtoList.add(questionPaperUtility.toQuestionPaperDto(qp));
        }

        return dtoList;
    }

    @Override
    @Transactional
    public QuestionPaperDTO updateQuestionPaperByTitle(String title, QuestionPaperDTO dto) {
        Optional<QuestionPaper> optionalQuestionPaper = questionPaperRepository.findByTitle(title);
        if (!optionalQuestionPaper.isPresent()) {
            throw new RuntimeException("Question Paper not found with title: " + title);
        }
//
        QuestionPaper existingQuestionPaper = optionalQuestionPaper.get();
//
//        existingQuestionPaper.setTitle(dto.getTitle());
//
//        existingQuestionPaper.setQuestions(questionUtility.toQuestionEntity(dto.getQuestions()));
//
//        Optional<Topic> topicOptional = topicRepository.findByTopicName(dto.getTopicDTO().getTopicName());
//        if (!topicOptional.isPresent()) {
//            throw new RuntimeException("Topic not found with name: " + dto.getTopicDTO().getTopicName());
//        }
//        existingQuestionPaper.setTopic(topicOptional.get());
//
        QuestionPaper updatedQuestionPaper = questionPaperRepository.save(existingQuestionPaper);
//
        return questionPaperUtility.toQuestionPaperDto(updatedQuestionPaper);
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
