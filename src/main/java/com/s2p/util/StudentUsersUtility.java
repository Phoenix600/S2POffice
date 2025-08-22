package com.s2p.util;

import com.s2p.master.Dto.StudentUsersDto;
import com.s2p.model.StudentUsers;

public class StudentUsersUtility
{
    public static StudentUsers toStudentUserEntity(StudentUsersDto studentUsersDto) {
        StudentUsers studentUsers = new StudentUsers();

        studentUsers.setStudentUserId(studentUsersDto.getStudentUserId());

        return studentUsers;
    }

    public static StudentUsersDto toStudentUserDto(StudentUsers studentUsers) {
        StudentUsersDto studentUsersDto = new StudentUsersDto();

        studentUsersDto.setStudentUserId(studentUsers.getStudentUserId());

        return studentUsersDto;
    }
}
