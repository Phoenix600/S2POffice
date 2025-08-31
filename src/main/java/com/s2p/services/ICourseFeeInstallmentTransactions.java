package com.s2p.services;

import com.s2p.dto.CourseFeeInstallmentTransactionsDto;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface ICourseFeeInstallmentTransactions
{
    CourseFeeInstallmentTransactionsDto createTransaction(CourseFeeInstallmentTransactionsDto dto);

    public abstract Set<CourseFeeInstallmentTransactionsDto> getAllCourseFeeInstallmentTransactions();

    public abstract List<CourseFeeInstallmentTransactionsDto> getTransactionsByCourseName(String courseName);

}