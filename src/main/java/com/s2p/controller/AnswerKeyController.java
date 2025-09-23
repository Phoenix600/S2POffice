package com.s2p.controller;

import com.s2p.constants.EOperationStatus;
import com.s2p.dto.AnswerKeyDTO;
import com.s2p.dto.ApiResponseDto;
import com.s2p.message.EApiResponseMessage;
import com.s2p.services.AnswerKeyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/answerKeys")
@Tag(name = "Answer Key Controller", description = "APIs for managing Answer Keys for Question Papers")
public class AnswerKeyController {

    private final AnswerKeyService answerKeyService;

    public AnswerKeyController(AnswerKeyService answerKeyService) {
        this.answerKeyService = answerKeyService;
    }

    @Operation(
            summary = "Create a new Answer Key",
            description = "This API creates a new answer key for a question paper using the details provided in the request body."
    )
    @PostMapping("/create")
    public ResponseEntity<ApiResponseDto<AnswerKeyDTO>> create(@RequestBody AnswerKeyDTO dto) {
        AnswerKeyDTO created = answerKeyService.createAnswerKey(dto);
        return ResponseEntity.ok(
                new ApiResponseDto<>(
                        EApiResponseMessage.DATA_SAVED.getMessage(),
                        EOperationStatus.RESULT_SUCCESS,
                        created
                )
        );
    }

    @Operation(
            summary = "Get Answer Key by Question Paper Title",
            description = "This API retrieves the answer key corresponding to a specific question paper title."
    )
    @GetMapping("/{title}")
    public ResponseEntity<ApiResponseDto<AnswerKeyDTO>> getByTitle(@PathVariable String title) {
        AnswerKeyDTO answerKey = answerKeyService.getAnswerKeyByQuestionPaperTitle(title);
        return ResponseEntity.ok(
                new ApiResponseDto<>(
                        EApiResponseMessage.DATA_FOUND.getMessage(),
                        EOperationStatus.RESULT_SUCCESS,
                        answerKey
                )
        );
    }

    @Operation(
            summary = "Get All Answer Keys",
            description = "This API retrieves a list of all answer keys available in the system."
    )
    @GetMapping
    public ResponseEntity<ApiResponseDto<List<AnswerKeyDTO>>> getAll() {
        List<AnswerKeyDTO> all = answerKeyService.getAllAnswerKeys();
        return ResponseEntity.ok(
                new ApiResponseDto<>(
                        EApiResponseMessage.DATA_FOUND.getMessage(),
                        EOperationStatus.RESULT_SUCCESS,
                        all
                )
        );
    }

    @Operation(
            summary = "Update Answer Key by Question Paper Title",
            description = "This API updates the answer key for a specific question paper identified by its title."
    )
    @PutMapping("/{title}")
    public ResponseEntity<ApiResponseDto<AnswerKeyDTO>> update(
            @PathVariable String title,
            @RequestBody AnswerKeyDTO dto) {
        AnswerKeyDTO updated = answerKeyService.updateAnswerKey(title, dto);
        return ResponseEntity.ok(
                new ApiResponseDto<>(
                        EApiResponseMessage.DATA_UPDATED.getMessage(),
                        EOperationStatus.RESULT_SUCCESS,
                        updated
                )
        );
    }

    @Operation(
            summary = "Delete Answer Key by Question Paper Title",
            description = "This API deletes an existing answer key identified by the question paper title."
    )
    @DeleteMapping("/{title}")
    public ResponseEntity<ApiResponseDto<Void>> delete(@PathVariable String title) {
        answerKeyService.deleteAnswerKey(title);
        return ResponseEntity.ok(
                new ApiResponseDto<>(
                        EApiResponseMessage.DATA_DELETED.getMessage(),
                        EOperationStatus.RESULT_SUCCESS,
                        null
                )
        );
    }
}
