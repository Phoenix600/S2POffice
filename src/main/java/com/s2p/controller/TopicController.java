package com.s2p.controller;

import com.s2p.constants.EOperationStatus;
import com.s2p.dto.ApiResponseDto;
import com.s2p.dto.TopicDTO;
import com.s2p.message.EApiResponseMessage;
import com.s2p.services.ITopicService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/topics")
@Tag(name = "Topic Management", description = "APIs for managing topics, including CRUD operations")
public class TopicController {

    private final ITopicService topicService;

    public TopicController(ITopicService topicService) {
        this.topicService = topicService;
    }

    @Operation(summary = "Create Topic", description = "Creates a new topic in the system")
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

    @Operation(summary = "Get Topic by Name", description = "Fetches a topic based on the provided topic name")
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

    @Operation(summary = "Get All Topics", description = "Retrieves all topics from the system")
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

    @Operation(summary = "Update Topic", description = "Updates the details of a topic identified by its name")
    @PutMapping("/{topicName}")
    public ResponseEntity<ApiResponseDto<TopicDTO>> update(
            @PathVariable String topicName,
            @RequestBody TopicDTO dto) {
        TopicDTO updated = topicService.updateTopicByTopicName(topicName, dto);
        return ResponseEntity.ok(
                new ApiResponseDto<>(
                        EApiResponseMessage.DATA_UPDATED.getMessage(),
                        EOperationStatus.RESULT_SUCCESS,
                        updated
                )
        );
    }

    @Operation(summary = "Delete Topic", description = "Deletes a topic from the system using its name")
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
