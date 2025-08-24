package com.s2p.repository.specifications;

import com.s2p.model.StudentInformation;
import com.s2p.repository.StudentUserRepository;
import org.springframework.data.jpa.domain.Specification;

public final class StudentSpecifications {

    private StudentSpecifications()
    {
        // Restrict Instantiation
    }

    public static Specification<StudentInformation> hasFirstName(String firstName) {
        return (root, query, cb) -> 
            firstName == null ? null : cb.like(cb.lower(root.get("firstName")), "%" + firstName.toLowerCase() + "%");
    }

    public static Specification<StudentInformation> hasLastName(String lastName) {
        return (root, query, cb) -> 
            lastName == null ? null : cb.like(cb.lower(root.get("lastName")), "%" + lastName.toLowerCase() + "%");
    }

    public static Specification<StudentInformation> hasEmail(String email) {
        return (root, query, cb) -> 
            email == null ? null : cb.equal(cb.lower(root.get("email")), email.toLowerCase());
    }

    public static Specification<StudentInformation> hasCollegeName(String collegeName) {
        return (root, query, cb) -> 
            collegeName == null ? null : cb.like(cb.lower(root.get("collegeName")), "%" + collegeName.toLowerCase() + "%");
    }

    public static Specification<StudentInformation> hasDegreeName(String degreeName) {
        return (root, query, cb) -> 
            degreeName == null ? null : cb.like(cb.lower(root.get("degreeName")), "%" + degreeName.toLowerCase() + "%");
    }

    public static Specification<StudentInformation> hasSemester(String semester) {
        return (root, query, cb) -> 
            semester == null ? null : cb.equal(cb.lower(root.get("semester")), semester.toLowerCase());
    }

    public static Specification<StudentInformation> hasPassingYear(String passingYear) {
        return (root, query, cb) -> 
            passingYear == null ? null : cb.equal(root.get("passingYear"), passingYear);
    }

    public static Specification<StudentInformation> isGraduated(Boolean isGraduated) {
        return (root, query, cb) -> 
            isGraduated == null ? null : cb.equal(root.get("isGraduated"), isGraduated);
    }
}
