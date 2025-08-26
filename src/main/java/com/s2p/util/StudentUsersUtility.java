package com.s2p.util;
import com.s2p.dto.StudentUserDto;
import com.s2p.model.StudentUsers;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentUsersUtility
{
    public abstract StudentUsers toStudentUserEntity(StudentUserDto studentUsersDto);
    public abstract StudentUserDto toStudentUserDto(StudentUsers studentUsers);

}