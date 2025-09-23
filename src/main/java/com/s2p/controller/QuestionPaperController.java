package com.s2p.controller;

import com.s2p.constants.EOperationStatus;
import com.s2p.dto.ApiResponseDto;
import com.s2p.dto.QuestionPaperDTO;
import com.s2p.message.EApiResponseMessage;
import com.s2p.services.IQuestionPaperService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//DONE

@RestController
@RequestMapping("/api/v1/question-papers")
public class QuestionPaperController {

    private final IQuestionPaperService questionPaperService;

    public QuestionPaperController(IQuestionPaperService questionPaperService) {
        this.questionPaperService = questionPaperService;
    }

    // --- CREATE ---DONE
    //http://localhost:8080/api/v1/question-papers/create
    @PostMapping("/create")
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

    // --- GET BY TITLE ---     DONE
    //http://localhost:8080/api/v1/question-papers/{title}
    @GetMapping("/{title}")
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

    // --- GET ALL ---  DONE
    @GetMapping
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

    // --- UPDATE ---  DONE
    //http://localhost:8080/api/v1/question-papers/{title}
    @PutMapping("/{title}")
    public ResponseEntity<ApiResponseDto<QuestionPaperDTO>> update(
            @PathVariable String title,
            @RequestBody QuestionPaperDTO dto) {
        QuestionPaperDTO updated = questionPaperService.updateQuestionPaper(title, dto);
        return ResponseEntity.ok(
                new ApiResponseDto<>(
                        EApiResponseMessage.DATA_UPDATED.getMessage(),
                        EOperationStatus.RESULT_SUCCESS,
                        updated
                )
        );
    }

    // --- DELETE ---   //DONE
    //http://localhost:8080/api/v1/question-papers/{title}
    @DeleteMapping("/{title}")
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
