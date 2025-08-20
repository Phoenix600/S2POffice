package com.s2p.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

/**
 * File Name: Admission.java
 * Entity: Admission
 * Package: com.s2p.model
 * Author: pranayramteke
 * Date: 19/08/25
 * Description:
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Admission extends BaseEntity
{
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "admission_id", updatable = false, nullable = false, columnDefinition = "BINARY(16)")
	private UUID admissionId;

    private LocalDate admissionDate;




}
