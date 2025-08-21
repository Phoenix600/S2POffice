package com.s2p.master.model;

import com.s2p.model.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * File Name: State.java
 * Entity: State
 * Package: com.s2p.model
 * Author: pranayramteke
 * Date: 19/08/25
 * Description:
 */

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class State extends BaseEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
	private UUID stateId;

    @Column(unique = true, nullable = false)
    private String stateName;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Set<City> cities = new HashSet<>();

}
