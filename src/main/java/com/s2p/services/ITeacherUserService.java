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

    Set<TeacherUserDto> getTeachersByBatch(UUID batchId);

    // Update a teacher (email/username/roles) and assign them to a batch
    TeacherUserDto updateTeacherByBatch(UUID batchId, UUID teacherId, TeacherUserDto dto);

    // Remove a teacher from a batch (but not delete teacher completely)
    void removeTeacherFromBatch(UUID batchId, UUID teacherId);

    Set<TeacherUserDto> getTeachersByCourse(UUID courseId);

    // Update a teacher (email/username/roles) and assign them to a course
    TeacherUserDto updateTeacherByCourse(UUID courseId, UUID teacherId, TeacherUserDto dto);

    // Remove a teacher from a course (but not delete teacher completely)
    void removeTeacherFromCourse(UUID courseId, UUID teacherId);

    // Delete a teacher completely (by teacherId)
    void deleteTeacherById(UUID teacherId);
}
