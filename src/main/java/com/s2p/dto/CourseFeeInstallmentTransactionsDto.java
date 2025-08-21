package com.s2p.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CourseFeeInstallmentTransactionsDto
{
    private UUID courseFeesID;

    private Long transactionId;

    private Double courseFees;

    private UUID CourseFeeInstallmentTransactions;
}
