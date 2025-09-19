package com.s2p.repository.specifications;

import com.s2p.model.Enquiry;
import com.s2p.model.StudentInformation;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.Join;

import java.time.LocalDate;

public class EnquirySpecification {

    public static Specification<Enquiry> hasStudentNameAndEmail(String firstName, String email) {
        return (root, query, cb) -> {
            // Join with StudentInformation
            Join<Enquiry, StudentInformation> studentInfo = root.join("studentInformation");

            return cb.and(
                    cb.equal(studentInfo.get("firstName"), firstName),
                    cb.equal(studentInfo.get("email"), email)
            );
        };
    }

    public static Specification<Enquiry> hasStudentEmail(String email) {
        return (root, query, cb) -> {
            Join<Enquiry, StudentInformation> studentInfo = root.join("studentInformation");
            return cb.equal(studentInfo.get("email"), email);
        };
    }

    public static Specification<Enquiry> hasStudentName(String firstName) {
        return (root, query, cb) -> {
            Join<Enquiry, StudentInformation> studentInfo = root.join("studentInformation");
            return cb.equal(studentInfo.get("firstName"), firstName);
        };
    }

    public static Specification<Enquiry> hasEnquiryDate(LocalDate date) {
        return (root, query, cb) -> cb.equal(root.get("enquiryDate"), date);
    }
}
