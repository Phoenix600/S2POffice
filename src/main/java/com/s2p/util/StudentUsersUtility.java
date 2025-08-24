package com.s2p.util;

import com.s2p.dto.StudentUserDto;
import com.s2p.master.dto.StudentUsersDto;
import com.s2p.model.StudentUsers;

public class StudentUsersUtility
{
    public static StudentUsers toStudentUserEntity(StudentUserDto studentUserDto) {
        StudentUsers studentUsers = new StudentUsers();

        studentUsers.setStudentUserId(studentUserDto.getStudentUserId());

        return studentUsers;
    }

    public static StudentUserDto toStudentUserDto(StudentUsers studentUsers) {
        StudentUserDto studentUserDto = new StudentUserDto();

        studentUserDto.setStudentUserId(studentUsers.getStudentUserId());

        return studentUserDto;
    }
}
