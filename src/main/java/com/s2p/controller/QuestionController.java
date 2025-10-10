package com.s2p.controller;

import com.s2p.constants.EOperationStatus;
import com.s2p.dto.ApiResponseDto;
import com.s2p.dto.QuestionDTO;
import com.s2p.dto.QuestionPaperDTO;
import com.s2p.message.EApiResponseMessage;
import com.s2p.services.IQuestionService;
import com.s2p.services.impl.QuestionPaperServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api/v1/questions")
@RequiredArgsConstructor
@Tag(name = "Question APIs", description = "CRUD operations for managing questions")
public class  QuestionController {

    private final IQuestionService questionService;

    private final QuestionPaperServiceImpl questionPaperService;

    @Operation(
            summary = "Create a new Question",
            description = "Adds a new question to the system"
    )
    @PostMapping("/create/{paper_title}")
    //http://localhost:8080/api/v1/questions/create/{paper_title}
    public ResponseEntity<ApiResponseDto<QuestionDTO>> create(@RequestBody QuestionDTO dto,@PathVariable("paper_title")String paperTitle ) {

        QuestionDTO created = questionService.createQuestion(dto,paperTitle);

        return ResponseEntity.ok(
                new ApiResponseDto<>(
                        EApiResponseMessage.DATA_SAVED.getMessage(),
                        EOperationStatus.RESULT_SUCCESS,
                        created
                )
        );
    }

    @Operation(
            summary = "Get Question by Text",
            description = "Fetches a question using its text as identifier"
    )
    @GetMapping("/get/{question_text}/{paper_title}")
// Example: http://localhost:8080/api/v1/questions/get/{question_title}/{paper_title}
    public ResponseEntity<ApiResponseDto<QuestionDTO>> getQuestionByTitle(
            @PathVariable("question_text") String questionText,
            @PathVariable("paper_title") String paperTitle) {

        QuestionDTO question = questionService.getQuestionByText(questionText, paperTitle);

        return ResponseEntity.ok(
                new ApiResponseDto<>(
                        EApiResponseMessage.DATA_FOUND.getMessage(),
                        EOperationStatus.RESULT_SUCCESS,
                        question
                )
        );
    }




    @Operation(
            summary = "Get All Questions",
            description = "Retrieves a list of all available questions"
    )
    @GetMapping("/all")
    //http://localhost:8080//api/v1/questions/all
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

    @Operation(
            summary = "Update Question",
            description = "Updates an existing question identified by its text"
    )
    @PutMapping("/update/{questionText}")
    //http://localhost:8080//api/v1/questions/update/{questionText}
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

    @Operation(
            summary = "Delete Question",
            description = "Deletes a question using its text as identifier"
    )
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
