package com.s2p.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class QuestionPaper extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID questionPaperId;

    @Column(nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "topic_id", nullable = false)
    private Topic topic;

    @OneToMany(mappedBy = "questionPaper", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Question> questions = new HashSet<>();

    @OneToOne(mappedBy = "questionPaper", cascade = CascadeType.ALL, orphanRemoval = true)
    private AnswerKey answerKey;
}
