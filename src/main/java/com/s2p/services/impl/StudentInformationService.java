package com.s2p.services.impl;

import com.s2p.dto.StudentInformationDto;
import com.s2p.model.StudentInformation;
import com.s2p.repository.StudentInformationRepository;
import com.s2p.repository.specifications.StudentSpecifications;
import com.s2p.services.IStudentInformationService;
import com.s2p.util.StudentInformationUtility;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import static com.s2p.repository.specifications.StudentSpecifications.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentInformationService implements IStudentInformationService
{
    private final StudentInformationRepository studentInformationRepository;
    private final StudentInformationUtility studentInformationUtility;

    @Override
    public StudentInformationDto createStudent(StudentInformationDto dto) {
        StudentInformation entity = studentInformationUtility.toStudentInformationEntity(dto);
        StudentInformation savedEntity = studentInformationRepository.save(entity);
        return studentInformationUtility.toStudentInformationDto(savedEntity);
    }

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
    public String deleteStudent(String email) {
        Optional<StudentInformation> optionalStudent = studentInformationRepository.findByEmail(email);

        if (!optionalStudent.isPresent()) {
            throw new RuntimeException("Student not found with email: " + email);
        }

        StudentInformation existingStudent = optionalStudent.get();
        studentInformationRepository.delete(existingStudent);

        return "Student with email " + email + " deleted successfully!";
    }

    @Override
    public StudentInformationDto getStudentByEmail(String email) {
        return null;
    }

    @Override
    public List<StudentInformationDto> getAllStudents() {
        return List.of();
    }

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
    public StudentInformationDto updateStudent(String email, StudentInformationDto dto) {
        Optional<StudentInformation> optionalStudent = studentInformationRepository.findByEmail(email);

        if (!optionalStudent.isPresent()) {
            throw new RuntimeException("Student not found with email: " + email);
        }

        StudentInformation existingStudent = optionalStudent.get();

        // set updated fields
        existingStudent.setFirstName(dto.getFirstName());
        existingStudent.setLastName(dto.getLastName());
        existingStudent.setCollegeName(dto.getCollegeName());
        existingStudent.setDepartName(dto.getDepartName());
        existingStudent.setSemester(dto.getSemester());
        existingStudent.setPassingYear(dto.getPassingYear());
        existingStudent.setIsGraduated(dto.getIsGraduated());
        existingStudent.setIsAdmitted(dto.getIsAdmitted());
        existingStudent.setIsDiscontinued(dto.getIsDiscontinued());
        existingStudent.setReasonOfDiscontinue(dto.getReasonOfDiscontinue());
        existingStudent.setEnquiry(dto.getEnquiry());
        existingStudent.setBatches(dto.getBatches());
        existingStudent.setCourses(dto.getCourses());
        existingStudent.setCourseFeeStructure(dto.getCourseFeeStructure());

        StudentInformation updated = studentInformationRepository.save(existingStudent);
        return studentInformationUtility.toStudentInformationDto(updated);
    }

    public void deleteStudentByEmail(String email) {
        Optional<StudentInformation> existingStudentOpt = studentInformationRepository.findByEmail(email);

        if (!existingStudentOpt.isPresent()) {
            throw new RuntimeException("Student not found with email: " + email);
        }

        studentInformationRepository.delete(existingStudentOpt.get());
    }



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

        List<StudentInformation> students = studentInformationRepository.findAll(spec);

        List<StudentInformationDto> result = new ArrayList<>();
        for (StudentInformation student : students) {
            result.add(studentInformationUtility.toStudentInformationDto(student));
        }

        return result;
    }


}
