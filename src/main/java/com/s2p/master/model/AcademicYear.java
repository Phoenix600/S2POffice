package com.s2p.master.model;

import com.s2p.model.CourseFees;
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
public class AcademicYear
{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID academicYearId;

    private Integer academicYear;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "course")
    private List<CourseFees> courseFees = new ArrayList<>();
}
