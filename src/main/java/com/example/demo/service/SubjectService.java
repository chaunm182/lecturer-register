package com.example.demo.service;

import com.example.demo.entity.Subject;

import java.util.List;

public interface SubjectService {
    List<Subject> findAll();
    List<Subject> findByFacultyId(Integer facultyId);
}
