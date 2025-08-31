package com.s2p.services;

import com.s2p.dto.AdminUserDto;

import java.util.Set;

public interface IAdminUserService
{
    public abstract AdminUserDto createAdminUser(AdminUserDto adminUserDto);

    public abstract AdminUserDto getAdminUserByUsername(String username);

    public abstract Set<AdminUserDto> getAllAdminUsers();

    public abstract AdminUserDto updateAdminUserByUsername(String username, AdminUserDto adminUserDto);

    public abstract AdminUserDto deleteAdminUserByUsername(String username);

}
