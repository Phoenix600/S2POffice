package com.s2p.controller;

import com.s2p.constants.EOperationStatus;
import com.s2p.dto.ApiResponseDto;
import com.s2p.dto.CourseFeeInstallmentTransactionsDto;
import com.s2p.message.EApiResponseMessage;
import com.s2p.services.impl.CourseFeeInstallmentTransactionsServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/v1/courseFeeInstallmentTransaction")
@Tag(name = "Course Fee Installment Transactions APIs", description = "APIs for managing course fee installment transactions")
public class CourseFeeInstallmentTransactionController {

    @Autowired
    CourseFeeInstallmentTransactionsServiceimpl courseFeeInstallmentTransactionsService;

    @Operation(summary = "Create Transaction", description = "Create a new course fee installment transaction")
    @PostMapping("/create-transaction")
    public ApiResponseDto<CourseFeeInstallmentTransactionsDto> createTransaction(
            @RequestBody CourseFeeInstallmentTransactionsDto dto) {

        CourseFeeInstallmentTransactionsDto saved = courseFeeInstallmentTransactionsService.createTransaction(dto);

        return new ApiResponseDto<>(
                EApiResponseMessage.DATA_SAVED.getMessage(),
                EOperationStatus.RESULT_SUCCESS,
                saved
        );
    }

    @Operation(summary = "Get All Transactions", description = "Fetch all course fee installment transactions")
    @GetMapping("/getAllTransactions")
    public ApiResponseDto<Set<CourseFeeInstallmentTransactionsDto>> getAllTransactions() {
        Set<CourseFeeInstallmentTransactionsDto> transactions = courseFeeInstallmentTransactionsService.getAllCourseFeeInstallmentTransactions();

        return new ApiResponseDto<>(
                EApiResponseMessage.DATA_FOUND.getMessage(),
                EOperationStatus.RESULT_SUCCESS,
                transactions
        );
    }

    @Operation(summary = "Get Transactions by Course Name", description = "Retrieve all installment transactions for a specific course")
    @GetMapping("/course/{courseName}")
    public ApiResponseDto<List<CourseFeeInstallmentTransactionsDto>> getTransactionsByCourseName(
            @PathVariable String courseName) {

        List<CourseFeeInstallmentTransactionsDto> transactions = courseFeeInstallmentTransactionsService.getTransactionsByCourseName(courseName);

        return new ApiResponseDto<>(
                EApiResponseMessage.DATA_FOUND.getMessage(),
                EOperationStatus.RESULT_SUCCESS,
                transactions
        );
    }
}
