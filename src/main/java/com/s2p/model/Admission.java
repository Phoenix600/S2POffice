package com.s2p.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
public class Admission extends BaseEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private UUID admissionId;

    private LocalDate admissionDate;




}
