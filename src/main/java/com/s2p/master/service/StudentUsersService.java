package com.s2p.master.service;

import com.s2p.master.dto.StudentUsersDto;

import java.util.List;
import java.util.UUID;

public interface StudentUsersService {
    StudentUsersDto createStudentUser(StudentUsersDto studentUsersDto);
    StudentUsersDto getStudentUserById(UUID studentUserId);
    List<StudentUsersDto> getAllStudentUsers();
    StudentUsersDto updateStudentUser(UUID studentUserId, StudentUsersDto studentUsersDto);
    void deleteStudentUser(UUID studentUserId);
}
