package com.s2p.services.impl;

import com.s2p.dto.CourseFeeInstallmentTransactionsDto;
import com.s2p.services.ICourseFeeInstallmentTransactions;

import java.util.Set;
import java.util.UUID;

public class CourseFeeInstallmentTransactionsService implements ICourseFeeInstallmentTransactions {

    @Override
    public CourseFeeInstallmentTransactionsDto getCourseFeeInstallmentTransactionById(UUID courseFeeInstallmentTransactions) {
        return null;
    }

    @Override
    public Set<CourseFeeInstallmentTransactionsDto> getAllCourseFeeInstallmentTransactions() {
        return Set.of();
    }

    @Override
    public CourseFeeInstallmentTransactionsDto deleteCourseFeeInstallmentTransactionById(UUID courseFeeInstallmentTransactions) {
        return null;
    }
}
