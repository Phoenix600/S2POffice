package com.s2p.util;

import com.s2p.dto.UsersDto;
import com.s2p.model.Users;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsersUtility
{
    public abstract Users toUsersEntity(UsersDto usersDto);
    public abstract UsersDto toUsersDto(Users users);

}
