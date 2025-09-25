package com.s2p.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Schema(description = "DTO representing a Topic")
public class TopicDTO {
    @Schema(description = "Unique ID of the topic", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
    private UUID topicId;

    @Schema(description = "Name of the topic", example = "Java Basics")
    private String topicName;

    @Schema(description = "ID of the parent course", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
    private CourseDto courseDto;
}
