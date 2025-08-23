package com.s2p.util;

import com.s2p.dto.CourseFeeInstallmentTransactionsDto;
import com.s2p.model.CourseFeeInstallmentTransactions;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class CourseFeeInstallmentTransactionsUtility
{
    public final static CourseFeeInstallmentTransactions toCourseFeeInstallmentTransactionsEntity(CourseFeeInstallmentTransactionsDto courseFeeInstallmentTransactionsDto)
    {
        CourseFeeInstallmentTransactions courseFeeInstallmentTransactions = new CourseFeeInstallmentTransactions();

        courseFeeInstallmentTransactions.setCourseFeeInstallmentTransactionsId(courseFeeInstallmentTransactionsDto.getCourseFeeInstallmentTransactionsId());

        return courseFeeInstallmentTransactions;
    }

    public final static CourseFeeInstallmentTransactionsDto toCourseFeeInstallmentTransactionsDto(CourseFeeInstallmentTransactions courseFeeInstallmentTransactions)
    {
        CourseFeeInstallmentTransactionsDto courseFeeInstallmentTransactionsDto = new CourseFeeInstallmentTransactionsDto();

        courseFeeInstallmentTransactionsDto.setCourseFeeInstallmentTransactionsId(courseFeeInstallmentTransactions.getCourseFeeInstallmentTransactionsId());

        return courseFeeInstallmentTransactionsDto;
    }
}
