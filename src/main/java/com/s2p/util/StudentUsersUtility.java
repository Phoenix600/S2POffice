package com.s2p.util;
import com.s2p.master.dto.StudentUsersDto;
import com.s2p.model.StudentUsers;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentUsersUtility
{
    public abstract StudentUsers toStudentUserEntity(StudentUsersDto studentUsersDto);
    public abstract StudentUsersDto toStudentUserDto(StudentUsers studentUsers);

}