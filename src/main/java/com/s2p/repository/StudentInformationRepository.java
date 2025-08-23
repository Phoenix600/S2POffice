package com.s2p.repository;

import com.s2p.model.StudentInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface StudentInformationRepository extends JpaRepository<StudentInformation,UUID>
{
    List<StudentInformation> findByAdmissionDate(LocalDate admissionDate);
}
