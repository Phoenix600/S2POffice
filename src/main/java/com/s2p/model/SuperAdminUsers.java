package com.s2p.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SuperAdminUsers extends Users {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID superAdminUserId;

//    @Column(unique = true)
//    private String email;
//
//    @Column(unique = true)
//    private String username;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "role_id", referencedColumnName = "rolesId")
    private Roles roles;
}
