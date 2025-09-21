package com.s2p.controller;

import com.s2p.constants.EOperationStatus;
import com.s2p.dto.ApiResponseDto;
import com.s2p.dto.QuestionDTO;
import com.s2p.message.EApiResponseMessage;
import com.s2p.services.IQuestionService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/questions")
@RequiredArgsConstructor
public class QuestionController {

    private final IQuestionService questionService;

    // --- CREATE ---   DONE
    //http://localhost:8080/api/v1/questions/create
    @PostMapping("/create")
    public ResponseEntity<ApiResponseDto<QuestionDTO>> create(@RequestBody QuestionDTO dto) {
        QuestionDTO created = questionService.createQuestion(dto);
        return ResponseEntity.ok(
                new ApiResponseDto<>(
                        EApiResponseMessage.DATA_SAVED.getMessage(),
                        EOperationStatus.RESULT_SUCCESS,
                        created
                )
        );
    }

    @GetMapping("/{questionText}")
    public ResponseEntity<ApiResponseDto<QuestionDTO>> getByText(@PathVariable String questionText) {
        QuestionDTO q = questionService.getQuestionByText(questionText);
        return ResponseEntity.ok(
                new ApiResponseDto<>(
                        EApiResponseMessage.DATA_FOUND.getMessage(),
                        EOperationStatus.RESULT_SUCCESS,
                        q
                )
        );
    }

    // --- GET ALL ---
    @GetMapping("all")
    public ResponseEntity<ApiResponseDto<List<QuestionDTO>>> getAll() {
        List<QuestionDTO> allQuestions = questionService.getAllQuestions();
        return ResponseEntity.ok(
                new ApiResponseDto<>(
                        EApiResponseMessage.DATA_FOUND.getMessage(),
                        EOperationStatus.RESULT_SUCCESS,
                        allQuestions
                )
        );
    }

    // --- UPDATE ---
    @PutMapping("/{questionText}")
    public ResponseEntity<ApiResponseDto<QuestionDTO>> update(
            @PathVariable String questionText,
            @RequestBody QuestionDTO dto) {
        QuestionDTO updated = questionService.updateQuestion(questionText, dto);
        return ResponseEntity.ok(
                new ApiResponseDto<>(
                        EApiResponseMessage.DATA_UPDATED.getMessage(),
                        EOperationStatus.RESULT_SUCCESS,
                        updated
                )
        );
    }

    // --- DELETE ---
    @DeleteMapping("/{questionText}")
    public ResponseEntity<ApiResponseDto<Void>> delete(@PathVariable String questionText) {
        questionService.deleteQuestion(questionText);
        return ResponseEntity.ok(
                new ApiResponseDto<>(
                        EApiResponseMessage.DATA_DELETED.getMessage(),
                        EOperationStatus.RESULT_SUCCESS,
                        null
                )
        );
    }
}
