package com.s2p.services;

import com.s2p.dto.TeacherUserDto;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface ITeacherUserService
{
    public abstract TeacherUserDto createTeacherUser(TeacherUserDto teacherUserDto);

    public abstract Set<TeacherUserDto> getAllTeachers ();

    public abstract Optional<TeacherUserDto> getTeacherUserByUsername(String username);

    public abstract TeacherUserDto updateTeacherUserByUsername(String username, TeacherUserDto teacherUserDto);

    public abstract void deleteTeacherUserByUsername(String username);
}
