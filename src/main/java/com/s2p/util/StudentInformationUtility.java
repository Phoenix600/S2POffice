package com.s2p.util;

import com.s2p.dto.StudentInformationDto;
import com.s2p.model.StudentInformation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



public class StudentInformationUtility
{
    public static final StudentInformation toStudentInformationEntity(StudentInformationDto studentInformationDto) {
        StudentInformation studentInformation = new StudentInformation();

        studentInformation.setStudentId(studentInformationDto.getStudentId());
        studentInformation.setFirstName(studentInformationDto.getFirstName());
        studentInformation.setLastName(studentInformation.getLastName());
        studentInformation.setEmail(studentInformationDto.getEmail());
        studentInformation.setCollegeName(studentInformationDto.getCollegeName());
        studentInformation.setDegreeName(studentInformationDto.getDegreeName());
        studentInformation.setPassingYear(studentInformationDto.getPassingYear());
        studentInformation.setIsGraduated(studentInformationDto.getIsGraduated());

        return studentInformation;
    }

    public static final StudentInformationDto toStudentInformationDto(StudentInformation studentInformation)
    {
        StudentInformationDto studentInformationDto = new StudentInformationDto();

        studentInformationDto.setStudentId(studentInformation.getStudentId());
        studentInformationDto.setFirstName(studentInformation.getFirstName());
        studentInformationDto.setLastName(studentInformation.getLastName());
        studentInformationDto.setEmail(studentInformation.getEmail());
        studentInformationDto.setCollegeName(studentInformation.getCollegeName());
        studentInformationDto.setDegreeName(studentInformation.getDegreeName());
        studentInformationDto.setPassingYear(studentInformation.getPassingYear());
        studentInformationDto.setIsGraduated(studentInformation.getIsGraduated());

        return studentInformationDto;
    }
}
