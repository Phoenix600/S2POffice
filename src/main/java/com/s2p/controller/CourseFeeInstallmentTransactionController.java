package com.s2p.controller;

import com.s2p.constants.EOperationStatus;
import com.s2p.dto.ApiResponseDto;
import com.s2p.dto.CourseFeeInstallmentTransactionsDto;
import com.s2p.message.EApiResponseMessage;
import com.s2p.services.impl.CourseFeeInstallmentTransactionsServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/courseFeeInstallmentTransaction")
public class CourseFeeInstallmentTransactionController
{
    @Autowired
    CourseFeeInstallmentTransactionsServiceimpl courseFeeInstallmentTransactionsService;

    // Create Transaction
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


    // Get All Transactions
    @GetMapping("/getAllTransactions")
    public ApiResponseDto<Set<CourseFeeInstallmentTransactionsDto>> getAllTransactions() {
        Set<CourseFeeInstallmentTransactionsDto> transactions = courseFeeInstallmentTransactionsService.getAllCourseFeeInstallmentTransactions();

        return new ApiResponseDto<>(
                EApiResponseMessage.DATA_FOUND.getMessage(),
                EOperationStatus.RESULT_SUCCESS,
                transactions
        );
    }

    // Get Transactions by Course Name
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
