package com.s2p.services.impl;

import com.s2p.dto.QuestionDTO;
import com.s2p.dto.QuestionPaperDTO;
import com.s2p.exceptions.ResourceNotFoundException;
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

import java.util.*;

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
        QuestionPaper questionPaper = questionPaperRepository.findByTitle(title)
                .orElseThrow(() -> new ResourceNotFoundException("Question Paper not found with title: " + title));

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
    public QuestionPaperDTO updateQuestionPaperByTitle(String title, QuestionPaperDTO questionPaperDTO) {
        Optional<QuestionPaper> optionalQuestionPaper = questionPaperRepository.findByTitle(title);
        if (!optionalQuestionPaper.isPresent()) {
            throw new RuntimeException("Question Paper not found with title: " + title);
        }

        QuestionPaper existingQuestionPaper = optionalQuestionPaper.get();

        // Update the title if provided
        if (questionPaperDTO.getTitle() != null && !questionPaperDTO.getTitle().isEmpty()) {
            existingQuestionPaper.setTitle(questionPaperDTO.getTitle());
        }

        // Update questions if provided
        if (questionPaperDTO.getQuestions() != null && !questionPaperDTO.getQuestions().isEmpty()) {
            existingQuestionPaper.setQuestions(Collections.singleton(questionUtility.toQuestionEntity((QuestionDTO) questionPaperDTO.getQuestions())));
        }

        // Update topic if provided
        if (questionPaperDTO.getTopicDTO() != null && questionPaperDTO.getTopicDTO().getTopicName() != null) {
            Optional<Topic> topicOptional = topicRepository.findByTopicName(questionPaperDTO.getTopicDTO().getTopicName());
            if (!topicOptional.isPresent()) {
                throw new RuntimeException("Topic not found with name: " + questionPaperDTO.getTopicDTO().getTopicName());
            }
            existingQuestionPaper.setTopic(topicOptional.get());
        }

        // Save updated entity
        QuestionPaper updatedQuestionPaper = questionPaperRepository.save(existingQuestionPaper);

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
