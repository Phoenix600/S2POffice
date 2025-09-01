package com.s2p.services;

import com.s2p.dto.StudentUserDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IStudentUserService
{
    public abstract StudentUserDto createStudentUser(StudentUserDto studentUsersDto);

    public abstract Optional<StudentUserDto> getStudentUserByUsername(String username);

    public abstract List<StudentUserDto> getAllStudentUsers();

    public abstract  StudentUserDto updateStudentUserByUsername(String username, StudentUserDto studentUserDto);

    public abstract void deleteStudentUserByUsername(String username);
}
