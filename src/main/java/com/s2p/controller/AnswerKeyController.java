package com.s2p.controller;

import com.s2p.constants.EOperationStatus;
import com.s2p.dto.AnswerKeyDTO;
import com.s2p.dto.ApiResponseDto;
import com.s2p.message.EApiResponseMessage;
import com.s2p.services.AnswerKeyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/answerKeys")
public class AnswerKeyController {

    private final AnswerKeyService answerKeyService;

    public AnswerKeyController(AnswerKeyService answerKeyService) {
        this.answerKeyService = answerKeyService;
    }

    // --- CREATE ---
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

    // --- GET BY QUESTION PAPER TITLE ---
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

    // --- GET ALL ---
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

    // --- UPDATE ---
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

    // --- DELETE ---
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
