package com.s2p.model;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;

import java.util.UUID;

@IdClass(CourseFees.class)
public class CourseFeeStructure
{
    @Id
    @Column(nullable = false)
    private UUID courseFeesID;

    @Id
    @Column(nullable = false)
    private Long transactionId;

    @Id
    @Column(nullable = false)
    private Double courseFees;


}
