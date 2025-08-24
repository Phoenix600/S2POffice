package com.s2p.services;

import com.s2p.dto.AdminUserDto;

import java.util.Set;
import java.util.UUID;

public interface IAdminUserService
{
    public abstract AdminUserDto createAdminUser(AdminUserDto adminUserDto);

    public abstract AdminUserDto getAdminUserById(UUID adminUserId);

    public abstract Set<AdminUserDto> getAllAdinUsers();

    public abstract AdminUserDto updateAdminUserById(UUID adminUserId, AdminUserDto adminUserDto);

    public abstract AdminUserDto deleteAdminUserById(UUID adminUserId);

}
