package com.s2p.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Embeddable
public class CourseFeeStructure
{

    @Column(nullable = false)
    private UUID courseFeesID;

    @Column(nullable = false)
    private Long transactionId;

    @Column(name = "course_fees", nullable = false)
    private Double courseFees;


}
