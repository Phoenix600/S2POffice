package com.s2p.util;

import com.s2p.dto.UsersDto;
import com.s2p.model.Users;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class UsersUtlity
{
    public final static Users toUsersEntity(UsersDto usersDto)
    {
        Users  users = new Users();

        users.setUsername(usersDto.getUsername());
        users.setEmail(usersDto.getEmail());
        users.setConfirmEmail(usersDto.getConfirmEmail());
        users.setPwd(usersDto.getPwd());
        users.setConfirmPwd(usersDto.getConfirmPwd());

        return users;
    }

    public final static UsersDto toUsersDto(Users users)
    {
        UsersDto usersDto = new UsersDto();

        usersDto.setUsername(users.getUsername());
        usersDto.setEmail(users.getEmail());
        usersDto.setConfirmEmail(users.getConfirmEmail());
        usersDto.setPwd(users.getPwd());
        usersDto.setConfirmPwd(users.getConfirmPwd());

        return usersDto;
    }
}
