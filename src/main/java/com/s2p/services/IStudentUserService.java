package com.s2p.services;

import com.s2p.dto.StudentUserDto;
import com.s2p.master.dto.StudentUsersDto;

import java.util.List;
import java.util.UUID;

public interface IStudentUserService
{
    public abstract StudentUserDto createStudentUser(StudentUserDto studentUsersDto);

    public abstract StudentUserDto getStudentUserById(UUID studentUserId);

    public abstract List<StudentUserDto> getAllStudentUsers();

    public abstract StudentUsersDto updateStudentUserById(UUID studentUserId);

    public abstract StudentUserDto deleteStudentUserById(UUID studentuserId);

}
