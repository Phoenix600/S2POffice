package com.s2p.dto;

import lombok.*;
import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class QuestionPaperDTO {
    private UUID questionPaperId;
    private String title;
    private UUID topicId;              // related topic
    private Set<UUID> questionIds;     // related questions
    private UUID answerKeyId;          // related answer key
}
