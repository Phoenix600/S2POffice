package com.s2p.model;

import io.swagger.v3.oas.annotations.media.Schema;
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

    @Column(nullable = false)
    private Integer obtainedMarks;

    private Boolean passed;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private StudentInformation student;

    @OneToOne
    @JoinColumn(name = "assessment_id", nullable = false)
    private Assessment assessment;
}
