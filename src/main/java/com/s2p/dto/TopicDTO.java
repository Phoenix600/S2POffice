package com.s2p.dto;

import lombok.*;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TopicDTO {
    private UUID topicId;
    private String topicName;
    private UUID courseId;   // refer to parent Course
}
