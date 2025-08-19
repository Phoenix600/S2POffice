package com.s2p.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

/**
 * File Name: CourseFeeInstallmentTransactions.java
 * Entity: CourseFeeInstallmentTransactions
 * Package: com.s2p.model
 * Author: pranayramteke
 * Date: 19/08/25
 * Description:
 */

@Entity
@IdClass(CourseFees.class)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CourseFeeInstallmentTransactions
{
    @Id
    private UUID courseFeesID;

    @Id
    private Long transactionId;

    @Id
    private Double courseFees;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID CourseFeeInstallmentTransactions;
}
