package com.s2p.repository;

import com.s2p.model.Enquiry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface EnquiryRepository extends JpaRepository<Enquiry,UUID>
{
    // fetch by student email
    Optional<Enquiry> findByStudentInformation_Email(String email);

    // delete by student email
    void deleteByStudentInformation_Email(String email);

    // fetch by enquiry date
    List<Enquiry> findByEnquiryDate(LocalDate enquiryDate);

    boolean existsByStudentInformation_Email(String email);
}
