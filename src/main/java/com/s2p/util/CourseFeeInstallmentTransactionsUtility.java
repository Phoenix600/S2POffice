package com.s2p.util;

import com.s2p.dto.CourseFeeInstallmentTransactionsDto;
import com.s2p.model.CourseFeeInstallmentTransactions;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourseFeeInstallmentTransactionsUtility
{
    public abstract CourseFeeInstallmentTransactions toCourseFeeInstallmentTransactionsEntity(CourseFeeInstallmentTransactionsDto courseFeeInstallmentTransactionsDto);
    public abstract CourseFeeInstallmentTransactionsDto toCourseFeeInstallmentTransactionsDto(CourseFeeInstallmentTransactions courseFeeInstallmentTransactions);

}
