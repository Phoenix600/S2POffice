package com.s2p.dto;

import lombok.*;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AssessmentDTO {
    private UUID assessmentId;
    private String title;
    private String description;
    private Integer totalMarks;
    private Integer passingMarks;
    private UUID courseId;
    private UUID topicId;
    private UUID questionPaperId;
}
