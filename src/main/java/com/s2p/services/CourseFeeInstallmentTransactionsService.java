package com.s2p.services;

import com.s2p.dto.CourseFeeInstallmentTransactionsDto;

import java.util.Set;
import java.util.UUID;

public class CourseFeeInstallmentTransactionsService implements ICourseFeeInstallmentTransactions{
    @Override
    public CourseFeeInstallmentTransactionsDto getTransactionById(UUID courseFeeInstallmentTransactions) {
        return null;
    }

    @Override
    public Set<CourseFeeInstallmentTransactionsDto> getAllCourseFeeInstallmentTransactions() {
        return Set.of();
    }
}
