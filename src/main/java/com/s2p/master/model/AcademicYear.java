package com.s2p.master.model;

import com.s2p.model.CourseFees;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Schema(description = "Represents an academic year and its associated course fees.")
public class AcademicYear
{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Schema(description = "Unique identifier for the academic year.",
            example = "550e8400-e29b-41d4-a716-446655440000")
    private UUID academicYearId;

    @Schema(description = "The numeric representation of the academic year.",
            example = "2025")
    private Integer academicYear;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "academicYear")
    @Column(nullable = true)
    @Schema(description = "List of course fees associated with this academic year.")
    private List<CourseFees> courseFees = new ArrayList<>();

}
