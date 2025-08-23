package com.s2p.util;

import com.s2p.dto.TeacherUserDto;
import com.s2p.model.TeacherUsers;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class TeacherUsersUtility
{
    public final static TeacherUsers toTeacherUsersEntity(TeacherUserDto teacherUserDto)
    {
        TeacherUsers teacherUsers = new TeacherUsers();

        teacherUsers.setTeacherUserId(teacherUserDto.getTeacherUserId());

        return teacherUsers;
    }

    public final static TeacherUserDto toTeacherUsersDto(TeacherUsers teacherUser)
    {
        TeacherUserDto teacherUsersDto = new TeacherUserDto();

        teacherUsersDto.setTeacherUserId(teacherUser.getTeacherUserId());

        return teacherUsersDto;
    }
}
