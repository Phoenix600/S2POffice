package com.s2p.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class AssessmentResult extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID resultId;

    private Integer obtainedMarks;

    private Boolean passed;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private StudentInformation student;

    @OneToOne
    @JoinColumn(name = "assessment_id", nullable = false)
    private Assessment assessment;
}
