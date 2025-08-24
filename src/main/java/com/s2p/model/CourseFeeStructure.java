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
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID courseFeeStructureId;

    @Column(name = "amount", nullable = false)
    private Double amount;

}
