package com.s2p.master;

import com.s2p.model.CourseFees;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

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

    @Column(nullable = false, unique = true)
    private Integer academicYear;

    @OneToMany(mappedBy = "academicYear", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CourseFees> courseFees = new ArrayList<>();
}
