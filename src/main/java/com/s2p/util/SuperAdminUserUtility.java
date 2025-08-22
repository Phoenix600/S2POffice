package com.s2p.util;

import com.s2p.dto.AdminUserDto;
import com.s2p.dto.SuperAdminUserDto;
import com.s2p.model.AdminUsers;
import com.s2p.model.SuperAdminUsers;

public class SuperAdminUserUtility {

    public static SuperAdminUsers toSuperAdminUserEntity(SuperAdminUserDto superAdminUserDto)
    {
        SuperAdminUsers superAdminUsers = new SuperAdminUsers();

        superAdminUsers.setSuperAdminUserId(superAdminUserDto.getSuperAdminUserId());
    // #TODO: Harsh Update All Utility Methods

        return superAdminUsers;
    }

    public static SuperAdminUserDto toSuperAdminUserDto(SuperAdminUsers superAdminUsers)
    {
        SuperAdminUserDto superAdminUserDto = new SuperAdminUserDto();

        superAdminUserDto.setSuperAdminUserId(superAdminUsers.getSuperAdminUserId());
        superAdminUserDto.setEmail(superAdminUsers.getEmail());
        superAdminUserDto.setUsername(superAdminUsers.getUsername());

        return superAdminUserDto;
    }

}

