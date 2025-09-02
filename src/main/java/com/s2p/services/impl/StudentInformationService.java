package com.s2p.services.impl;

import com.s2p.dto.StudentInformationDto;
import com.s2p.exceptions.ResourceNotFoundException;
import com.s2p.model.StudentInformation;
import com.s2p.repository.StudentInformationRepository;
import com.s2p.repository.specifications.StudentSpecifications;
import com.s2p.services.IStudentInformationService;
import com.s2p.util.StudentInformationUtility;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.s2p.repository.specifications.StudentSpecifications.*;



@Service
public class StudentInformationService implements IStudentInformationService
{
    @Autowired
    StudentInformationRepository studentInformationRepository;

    @Autowired
    StudentInformationUtility studentInformationUtility;


    @Override
    public StudentInformationDto createStudentInformation(StudentInformationDto studentInformationDto) throws BadRequestException {
            List<StudentInformation> allStudents = studentInformationRepository.findAll();

            for (StudentInformation existingStudent : allStudents) {
                if (existingStudent.getEmail().equalsIgnoreCase(studentInformationDto.getEmail())) {
                    throw new BadRequestException(
                            "Student already exists with email: " + studentInformationDto.getEmail()
                    );
                }
            }

            StudentInformation student = studentInformationUtility.toStudentInformationEntity(studentInformationDto);
            StudentInformation savedStudent = studentInformationRepository.save(student);

            return studentInformationUtility.toStudentInformationDto(savedStudent);
    }

    @Override
    public Optional<StudentInformationDto> getStudentByEmail(String email) {
        Optional<StudentInformation> studentOpt = studentInformationRepository.findByEmail(email);

        if (studentOpt.isPresent()) {
            StudentInformationDto dto = studentInformationUtility.toStudentInformationDto(studentOpt.get());
            return Optional.of(dto);
        }

        return Optional.empty();
    }


    @Override
    public Set<StudentInformationDto> getAllStudents()
    {
        List<StudentInformation> allStudents = studentInformationRepository.findAll();
        Set<StudentInformationDto> result = new HashSet<>();

        for (StudentInformation student : allStudents) {
            result.add(studentInformationUtility.toStudentInformationDto(student));
        }

        return result;
    }

    @Override
    public StudentInformationDto updateStudentByEmail(String email, StudentInformationDto studentInformationDto)
    {
        Optional<StudentInformation> existingStudentOpt = studentInformationRepository.findByEmail(email);

        if (existingStudentOpt.isEmpty()) {
            throw new RuntimeException("Student not found with email: " + email);
        }

        StudentInformation existingStudent = existingStudentOpt.get();

        existingStudent.setFirstName(studentInformationDto.getFirstName());
        existingStudent.setLastName(studentInformationDto.getLastName());
        existingStudent.setCollegeName(studentInformationDto.getCollegeName());
        existingStudent.setDepartName(studentInformationDto.getDepartName());
        existingStudent.setSemester(studentInformationDto.getSemester());
        existingStudent.setPassingYear(studentInformationDto.getPassingYear());
        existingStudent.setIsGraduated(studentInformationDto.getIsGraduated());
        existingStudent.setIsAdmitted(studentInformationDto.getIsAdmitted());
        existingStudent.setIsDiscontinued(studentInformationDto.getIsDiscontinued());
        existingStudent.setReasonOfDiscontinue(studentInformationDto.getReasonOfDiscontinue());
        existingStudent.setEnquiry(studentInformationDto.getEnquiry());
        existingStudent.setBatches(studentInformationDto.getBatches());
        existingStudent.setCourses(studentInformationDto.getCourses());
        existingStudent.setCourseFeeStructure(studentInformationDto.getCourseFeeStructure());

        StudentInformation updatedEntity = studentInformationRepository.save(existingStudent);
        return studentInformationUtility.toStudentInformationDto(updatedEntity);
    }

    @Override
    public void deleteStudentByEmail(String email) {
        Optional<StudentInformation> existingStudentOpt = studentInformationRepository.findByEmail(email);

        if (!existingStudentOpt.isPresent()) {
            throw new RuntimeException("Student not found with email: " + email);
        }

        studentInformationRepository.delete(existingStudentOpt.get());
    }

    @Override
    public List<StudentInformationDto> searchStudents(
            String firstName,
            String lastName,
            String email,
            String collegeName,
            String degreeName,
            String semester,
            String passingYear,
            Boolean isGraduated) {

        Specification<StudentInformation> spec = Specification.anyOf(
                hasFirstName(firstName),
                hasLastName(lastName),
                hasEmail(email),
                StudentSpecifications.hasCollegeName(collegeName),
                StudentSpecifications.hasDegreeName(degreeName),
                StudentSpecifications.hasSemester(semester),
                StudentSpecifications.hasPassingYear(passingYear),
                StudentSpecifications.isGraduated(isGraduated)
        );
//                .where(hasFirstName(firstName))
//                .and(hasLastName(lastName))
//                .and(hasEmail(email))
//                .and(hasCollegeName(collegeName))
//                .and(hasDegreeName(degreeName))
//                .and(hasSemester(semester))
//                .and(hasPassingYear(passingYear))
//                .and(isGraduated(isGraduated));

        List<StudentInformation> students = studentInformationRepository.findAll(spec);

        List<StudentInformationDto> result = new ArrayList<>();
        for (StudentInformation student : students) {
            result.add(studentInformationUtility.toStudentInformationDto(student));
        }

        return result;
    }


}
