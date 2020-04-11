package com.example.demo.repository;

import com.example.demo.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject,Integer> {
    List<Subject> findByFacultyId(Integer facultyId);
}
