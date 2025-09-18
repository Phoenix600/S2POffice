package com.s2p.repository;

import com.s2p.model.TeacherUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
import java.util.List;

@Repository
public interface TeacherUserRepository extends JpaRepository<TeacherUsers, UUID>
{
    public abstract Optional<TeacherUsers> findByEmail(String email);
    public abstract Optional<TeacherUsers> findByUsername(String username);
    public abstract List<TeacherUsers> findByBatch_BatchId(UUID batchId);  // Add this line
    public abstract List<TeacherUsers> findByCourses_CourseId(UUID courseId);
}
