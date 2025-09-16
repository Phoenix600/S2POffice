package com.s2p.controller;

import com.s2p.constants.EOperationStatus;
import com.s2p.dto.ApiResponseDto;
import com.s2p.dto.TopicDTO;
import com.s2p.message.EApiResponseMessage;
import com.s2p.services.ITopicService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/topics")
public class TopicController {

    private final ITopicService topicService;

    public TopicController(ITopicService topicService) {
        this.topicService = topicService;
    }

    // --- CREATE ---
    @PostMapping("/create")
    public ResponseEntity<ApiResponseDto<TopicDTO>> create(@RequestBody TopicDTO dto) {
        TopicDTO created = topicService.createTopic(dto);
        return ResponseEntity.ok(
                new ApiResponseDto<>(
                        EApiResponseMessage.DATA_SAVED.getMessage(),
                        EOperationStatus.RESULT_SUCCESS,
                        created
                )
        );
    }

    // --- GET BY NAME ---
    @GetMapping("/{topicName}")
    public ResponseEntity<ApiResponseDto<TopicDTO>> getByName(@PathVariable String topicName) {
        TopicDTO topic = topicService.getTopicByName(topicName);
        return ResponseEntity.ok(
                new ApiResponseDto<>(
                        EApiResponseMessage.DATA_FOUND.getMessage(),
                        EOperationStatus.RESULT_SUCCESS,
                        topic
                )
        );
    }

    // --- GET ALL ---
    @GetMapping
    public ResponseEntity<ApiResponseDto<List<TopicDTO>>> getAll() {
        List<TopicDTO> topics = topicService.getAllTopics();
        return ResponseEntity.ok(
                new ApiResponseDto<>(
                        EApiResponseMessage.DATA_FOUND.getMessage(),
                        EOperationStatus.RESULT_SUCCESS,
                        topics
                )
        );
    }

    // --- UPDATE ---
    @PutMapping("/{topicName}")
    public ResponseEntity<ApiResponseDto<TopicDTO>> update(
            @PathVariable String topicName,
            @RequestBody TopicDTO dto) {
        TopicDTO updated = topicService.updateTopic(topicName, dto);
        return ResponseEntity.ok(
                new ApiResponseDto<>(
                        EApiResponseMessage.DATA_UPDATED.getMessage(),
                        EOperationStatus.RESULT_SUCCESS,
                        updated
                )
        );
    }

    // --- DELETE ---
    @DeleteMapping("/{topicName}")
    public ResponseEntity<ApiResponseDto<Void>> delete(@PathVariable String topicName) {
        topicService.deleteTopic(topicName);
        return ResponseEntity.ok(
                new ApiResponseDto<>(
                        EApiResponseMessage.DATA_DELETED.getMessage(),
                        EOperationStatus.RESULT_SUCCESS,
                        null
                )
        );
    }
}
