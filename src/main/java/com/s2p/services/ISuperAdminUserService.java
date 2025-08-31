package com.s2p.services;

import com.s2p.dto.SuperAdminUserDto;
import jakarta.persistence.SecondaryTable;

import java.util.Set;
import java.util.UUID;

public interface ISuperAdminUserService
{
    public abstract SuperAdminUserDto createSuperAdminUser(SuperAdminUserDto superAdminUserDto);

    public abstract SuperAdminUserDto getSuperAdminUserById(UUID superAdminUserId);

    public abstract Set<SuperAdminUserDto> getAllSuperAdminUsers();

    public abstract SuperAdminUserDto updateSuperAdminUserById(UUID superAdminUserId);

    public abstract SuperAdminUserDto deleteSuperAdminById(UUID superAdminUserId);

}
