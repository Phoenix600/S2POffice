package com.s2p.master.model;

import com.s2p.model.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

/**
 * File Name: College.java
 * Entity: College
 * Package: com.s2p.model
 * Author: pranayramteke
 * Date: 19/08/25
 * Description:
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class College extends BaseEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
	private UUID collegeId;

    @Column(nullable = false, unique = true)
    private String collegeName;

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "college_department",
            joinColumns = { @JoinColumn(name = "college_id")    },
            inverseJoinColumns = { @JoinColumn(name = "department_id" )}
    )
    private Set<Department> departmentSet = new LinkedHashSet<>();
}
