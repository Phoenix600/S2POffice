package com.s2p.util;

import com.s2p.dto.AdminUserDto;
import com.s2p.dto.SuperAdminUserDto;
import com.s2p.model.AdminUsers;
import com.s2p.model.SuperAdminUsers;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class SuperAdminUserUtility {

    public final static SuperAdminUsers toSuperAdminUserEntity(SuperAdminUserDto superAdminUserDto)
    {
        SuperAdminUsers superAdminUsers = new SuperAdminUsers();

        superAdminUsers.setSuperAdminUserId(superAdminUserDto.getSuperAdminUserId());
        superAdminUsers.setEmail(superAdminUserDto.getEmail());
        superAdminUsers.setUsername(superAdminUserDto.getUsername());


        return superAdminUsers;
    }

    public final static SuperAdminUserDto toSuperAdminUserDto(SuperAdminUsers superAdminUsers)
    {
        SuperAdminUserDto superAdminUserDto = new SuperAdminUserDto();

        superAdminUserDto.setSuperAdminUserId(superAdminUsers.getSuperAdminUserId());
        superAdminUserDto.setEmail(superAdminUsers.getEmail());
        superAdminUserDto.setUsername(superAdminUsers.getUsername());

        return superAdminUserDto;
    }

}

