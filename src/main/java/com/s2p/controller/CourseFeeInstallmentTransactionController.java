package com.s2p.controller;

import com.s2p.constants.EOperationStatus;
import com.s2p.dto.ApiResponseDto;
import com.s2p.dto.CourseFeeInstallmentTransactionsDto;
import com.s2p.message.EApiResponseMessage;
import com.s2p.services.impl.CourseFeeInstallmentTransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/courseFeeInstallmentTransaction")
public class CourseFeeInstallmentTransactionController
{
    @Autowired
    CourseFeeInstallmentTransactionsService courseFeeInstallmentTransactionsService;

    //  Get transaction by ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDto<CourseFeeInstallmentTransactionsDto>> getTransactionById(
            @PathVariable("id") UUID id) {

        CourseFeeInstallmentTransactionsDto transaction = courseFeeInstallmentTransactionsService.getCourseFeeInstallmentTransactionById(id);

        ApiResponseDto<CourseFeeInstallmentTransactionsDto> response = new ApiResponseDto<>();
        response.setStatus(EOperationStatus.RESULT_SUCCESS);
        response.setMessage(EApiResponseMessage.DATA_FOUND.getMessage());
        response.setData(transaction);

        return ResponseEntity.ok(response);
    }

    // Get all transactions
    @GetMapping
    public ResponseEntity<ApiResponseDto<Set<CourseFeeInstallmentTransactionsDto>>> getAllTransactions() {
        Set<CourseFeeInstallmentTransactionsDto> transactions = courseFeeInstallmentTransactionsService.getAllCourseFeeInstallmentTransactions();

        ApiResponseDto<Set<CourseFeeInstallmentTransactionsDto>> response = new ApiResponseDto<>();
        response.setStatus(EOperationStatus.RESULT_SUCCESS);
        response.setMessage(EApiResponseMessage.DATA_FOUND.getMessage());
        response.setData(transactions);

        return ResponseEntity.ok(response);
    }

    // Delete transaction by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDto<String>> deleteTransactionById(@PathVariable("id") UUID id) {
        courseFeeInstallmentTransactionsService.deleteCourseFeeInstallmentTransactionById(id);

        ApiResponseDto<String> response = new ApiResponseDto<>();
        response.setStatus(EOperationStatus.RESULT_SUCCESS);
        response.setMessage(EApiResponseMessage.DATA_DELETED.getMessage());
        response.setData("Course Fee Installment Transaction deleted successfully");

        return ResponseEntity.ok(response);
    }


}
