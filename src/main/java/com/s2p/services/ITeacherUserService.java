package com.s2p.services;

import com.s2p.dto.TeacherUserDto;

import java.util.Set;
import java.util.UUID;

public interface ITeacherUserService
{
    public abstract TeacherUserDto createTeacherUser(TeacherUserDto teacherUserDto);

    public abstract TeacherUserDto getTeacherById(UUID teacherUserId);

    public abstract Set<TeacherUserDto> getAllTeachers ();

    public abstract TeacherUserDto updateTeacherUserById(UUID teacherUserId);

    public abstract TeacherUserDto deleteTeacherUserById(UUID teacherUserId);
}
