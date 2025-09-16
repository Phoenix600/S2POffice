package com.s2p.dto;

import lombok.*;
import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AnswerKeyDTO {
    private UUID answerKeyId;
    private Map<Integer, String> answers;   // questionNo → correctOption
    private UUID questionPaperId;
}
