package com.StudentIS.StudentIS.repo;

import com.StudentIS.StudentIS.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    boolean existsByEmail(String email);
    boolean existsByIndexNo(String indexNo);
}