package com.s2p.repository;

import com.s2p.model.Assessment;
import com.s2p.model.AssessmentResult;
import com.s2p.model.StudentInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AssessmentResultRepository extends JpaRepository<AssessmentResult, UUID> {

    List<AssessmentResult> findByStudent(StudentInformation student);

    Optional<AssessmentResult> findByStudentAndAssessment(StudentInformation student, Assessment assessment);
}
