package com.s2p.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Schema(description = "DTO representing a Student Admission")
public class AdmissionDto {

    @Schema(description = "Unique ID of the Admission record", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
    private UUID admissionId;

    @Schema(description = "Date of admission", example = "2025-08-19")
    private LocalDate admissionDate;
}
