package com.s2p.dto.masterDto;

import com.s2p.master.CollegeBranch;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CollegeDto
{
    private UUID collegeId;

    private String collegeName;

    private Set<CollegeBranch> collegeBranchSet = new LinkedHashSet<>();
}
