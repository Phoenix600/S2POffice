package com.s2p.model;

import com.s2p.dto.RolesDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.naming.Name;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StudentUsers extends Users
{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID studentUserId;

    private String email;

    private String username;

    private Roles roles;


}