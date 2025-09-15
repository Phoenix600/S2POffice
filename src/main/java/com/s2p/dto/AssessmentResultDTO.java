package com.s2p.dto;

import com.s2p.model.Assessment;
import com.s2p.model.StudentInformation;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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

    private StudentInformation student;

    private Assessment assessment;
}
