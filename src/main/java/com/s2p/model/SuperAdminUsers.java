package com.s2p.model;

import com.s2p.dto.RolesDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class SuperAdminUsers extends Users
{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID superAdminUserId;

    private String email;

    private String username;


}