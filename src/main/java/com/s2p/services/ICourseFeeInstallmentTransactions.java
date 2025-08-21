package com.s2p.services;

import com.s2p.dto.CourseFeeInstallmentTransactionsDto;

import java.util.Set;
import java.util.UUID;

public interface ICourseFeeInstallmentTransactions
{
    public abstract CourseFeeInstallmentTransactionsDto getTransactionById(UUID courseFeeInstallmentTransactions);

    public abstract Set<CourseFeeInstallmentTransactionsDto> getAllCourseFeeInstallmentTransactions();
}