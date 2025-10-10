package com.s2p.controller;

import com.s2p.constants.EOperationStatus;
import com.s2p.dto.ApiResponseDto;
import com.s2p.dto.QuestionPaperDTO;
import com.s2p.message.EApiResponseMessage;
import com.s2p.services.IQuestionPaperService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/question-papers")
@Tag(name = "Question Paper Management", description = "APIs for creating, retrieving, updating, and deleting Question Papers")
public class QuestionPaperController {

    private final IQuestionPaperService questionPaperService;

    public QuestionPaperController(IQuestionPaperService questionPaperService) {
        this.questionPaperService = questionPaperService;
    }

    @PostMapping("/create-questionpaper")
    @Operation(
            summary = "Create a new Question Paper",
            description = "Creates a new Question Paper based on the provided details."
    )
    //
    public ResponseEntity<ApiResponseDto<QuestionPaperDTO>> create(@RequestBody QuestionPaperDTO dto) {
        QuestionPaperDTO created = questionPaperService.createQuestionPaper(dto);
        return ResponseEntity.ok(
                new ApiResponseDto<>(
                        EApiResponseMessage.DATA_SAVED.getMessage(),
                        EOperationStatus.RESULT_SUCCESS,
                        created
                )
        );
    }

    @GetMapping("/{title}")
    @Operation(
            summary = "Get Question Paper by Title",
            description = "Retrieves a specific Question Paper using its title."
    )
    //http://localhost:8080/api/v1/question-papers/{title}
    public ResponseEntity<ApiResponseDto<QuestionPaperDTO>> getByTitle(@PathVariable String title) {
        QuestionPaperDTO qp = questionPaperService.getQuestionPaperByTitle(title);
        return ResponseEntity.ok(
                new ApiResponseDto<>(
                        EApiResponseMessage.DATA_FOUND.getMessage(),
                        EOperationStatus.RESULT_SUCCESS,
                        qp
                )
        );
    }

    @GetMapping("all")
    @Operation(
            summary = "Get all Question Papers",
            description = "Fetches a list of all available Question Papers."
    )
    //http://localhost:8080/api/v1/question-papers/all
    public ResponseEntity<ApiResponseDto<List<QuestionPaperDTO>>> getAll() {
        List<QuestionPaperDTO> allQPs = questionPaperService.getAllQuestionPapers();
        return ResponseEntity.ok(
                new ApiResponseDto<>(
                        EApiResponseMessage.DATA_FOUND.getMessage(),
                        EOperationStatus.RESULT_SUCCESS,
                        allQPs
                )
        );
    }

    @PutMapping("update/{title}")
    @Operation(
            summary = "Update Question Paper",
            description = "Updates the details of an existing Question Paper using its title."
    )
    //http://localhost:8080/api/v1/question-papers/update/{title}
    public ResponseEntity<ApiResponseDto<QuestionPaperDTO>> update(
            @PathVariable String title,
            @RequestBody QuestionPaperDTO dto) {
        QuestionPaperDTO updated = questionPaperService.updateQuestionPaperByTitle(title, dto);
        return ResponseEntity.ok(
                new ApiResponseDto<>(
                        EApiResponseMessage.DATA_UPDATED.getMessage(),
                        EOperationStatus.RESULT_SUCCESS,
                        updated
                )
        );
    }

    @DeleteMapping("delete/{title}")
    @Operation(
            summary = "Delete Question Paper",
            description = "Deletes a specific Question Paper using its title."
    )
    //http://localhost:8080/api/v1/question-papers/delete/{title}
    public ResponseEntity<ApiResponseDto<Void>> delete(@PathVariable String title) {
        questionPaperService.deleteQuestionPaper(title);
        return ResponseEntity.ok(
                new ApiResponseDto<>(
                        EApiResponseMessage.DATA_DELETED.getMessage(),
                        EOperationStatus.RESULT_SUCCESS,
                        null
                )
        );
    }
}
