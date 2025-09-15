package com.s2p.dto;

import lombok.*;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AssessmentResultDTO {
    private UUID resultId;
    private Integer obtainedMarks;
    private Boolean passed;
    private UUID studentId;
    private UUID assessmentId;
}
