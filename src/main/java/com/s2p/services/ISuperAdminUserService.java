package com.s2p.services;

import com.s2p.dto.SuperAdminUserDto;
import jakarta.persistence.SecondaryTable;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface ISuperAdminUserService
{
    public abstract SuperAdminUserDto createSuperAdminUser(SuperAdminUserDto superAdminUserDto);

    public abstract Optional<SuperAdminUserDto> getSuperAdminUserByUsername(String username);

    public abstract Set<SuperAdminUserDto> getAllSuperAdminUsers();

    public abstract SuperAdminUserDto updateSuperAdminUserByUsername(String username, SuperAdminUserDto superAdminUserDto);

    public abstract void deleteSuperAdminUserByUsername(String username);

}
