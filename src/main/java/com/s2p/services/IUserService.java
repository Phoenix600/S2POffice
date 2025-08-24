package com.s2p.services;

import com.s2p.dto.UsersDto;

public interface IUserService
{
    public abstract UsersDto createUser(UsersDto usersDto);

    public abstract UsersDto getUserByEmailId(String email);
}
