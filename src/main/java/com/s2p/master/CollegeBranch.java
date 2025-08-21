package com.s2p.master;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CollegeBranch
{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID collegeBranchId;

    @Column(unique = true, nullable = false)
    private String collegeBranchName;

    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    private Set<College> college = new HashSet<>();
}
