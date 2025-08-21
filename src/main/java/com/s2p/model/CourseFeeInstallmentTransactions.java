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
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CourseFeeInstallmentTransactions
{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID courseFeeInstallmentTransactions;
}
