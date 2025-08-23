package com.s2p.master;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @Column(nullable = false, unique = true)
    private Integer academicYear;
}
