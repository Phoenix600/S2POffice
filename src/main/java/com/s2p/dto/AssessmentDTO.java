package com.s2p.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Schema(description = "DTO representing an Assessment entity")
public class AssessmentDTO {

    @Schema(description = "Unique ID of the Assessment", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
    private UUID assessmentId;

    @Schema(description = "Title of the Assessment", example = "Mid-Term Exam")
    private String title;

    @Schema(description = "Detailed description of the Assessment")
    private String description;

    @Schema(description = "Total marks for the Assessment", example = "100")
    private Integer totalMarks;

    @Schema(description = "Passing marks required for the Assessment", example = "35")
    private Integer passingMarks;

    @Schema(description = "Course associated with the Assessment")
    private CourseDto courseDto;

    @Schema(description = "Topic associated with the Assessment")
    private TopicDTO topicDTO;

    @Schema(description = "Question paper associated with the Assessment")
    private QuestionPaperDTO questionPaperDto;

    public UUID getCourseId() {
        return courseDto != null ? courseDto.getCourseId() : null;
    }

    public UUID getTopicId() {
        return topicDTO != null ? topicDTO.getTopicId() : null;
    }

    public UUID getQuestionPaperId() {
        return questionPaperDto != null ? questionPaperDto.getQuestionPaperId() : null;
    }


}
