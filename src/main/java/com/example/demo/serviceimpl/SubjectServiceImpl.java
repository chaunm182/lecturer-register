package com.example.demo.serviceimpl;

import com.example.demo.entity.Subject;
import com.example.demo.repository.SubjectRepository;
import com.example.demo.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;
    @Override
    public List<Subject> findAll() {
        Sort sort = Sort.by("subjectId");
        sort = sort.ascending();
        return subjectRepository.findAll(sort);
    }

    @Override
    public List<Subject> findByFacultyId(Integer facultyId) {
        return subjectRepository.findByFacultyId(facultyId);
    }
}
