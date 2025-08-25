package com.s2p.util;

import com.s2p.dto.TeacherUserDto;
import com.s2p.model.TeacherUsers;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TeacherUsersUtility
{
    public abstract TeacherUsers toTeacherUsersEntity(TeacherUserDto teacherUserDto);

    public abstract TeacherUserDto toTeacherUsersDto(TeacherUsers teacherUser);

}
