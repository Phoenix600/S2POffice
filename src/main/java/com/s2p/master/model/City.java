package com.s2p.master.model;

import com.s2p.model.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

/**
 * File Name: City.java
 * Entity: City
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
public class City extends BaseEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID cityId;
    private String stateName;
}
