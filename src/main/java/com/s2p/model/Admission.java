package com.s2p.model;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Entity representing the Admissions Of Students")
public class Admission extends BaseEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "admission_id", updatable = false, nullable = false, columnDefinition = "BINARY(16)")
  	private UUID admissionId;

    @Column(nullable = false)
    private LocalDate admissionDate;

}
