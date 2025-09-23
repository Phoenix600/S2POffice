package com.s2p.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Schema(description = "Entity representing a Topic within a Course")
public class Topic extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Schema(description = "Unique identifier for the Topic", example = "a12b34cd-56ef-78gh-90ij-klmnopqrstuv")
    private UUID topicId;

    @Column(nullable = false)
    @Schema(description = "Name of the Topic", example = "Introduction to Java")
    private String topicName;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    @Schema(description = "Course to which this Topic belongs")
    private Course course;

    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL, orphanRemoval = true)
    @Schema(description = "Set of Question Papers associated with this Topic")
    private Set<QuestionPaper> questionPapers = new HashSet<>();
}
