package com.s2p.master;

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
 * File Name: Country.java
 * Entity: Country
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
public class Country extends BaseEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
	private UUID countryId;

    @Column(nullable = false, unique = true)
    private String country;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Set<State> states = new HashSet<>();

}
