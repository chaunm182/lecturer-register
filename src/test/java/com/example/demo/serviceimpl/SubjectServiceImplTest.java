package com.example.demo.serviceimpl;

import com.example.demo.entity.Subject;
import com.example.demo.repository.SubjectRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;

class SubjectServiceImplTest {

    @InjectMocks
    SubjectServiceImpl subjectService;

    @Mock
    SubjectRepository subjectRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void findAll() {
        List<Subject> subjectList = new ArrayList<>();
        Subject subject1 = new Subject();
        subject1.setId(1);
        Subject subject2  = new Subject();
        subject2.setId(2);
        subjectList.add(subject1);
        subjectList.add(subject2);

        Sort sort= Sort.by("subjectId");
        when(subjectRepository.findAll(sort)).thenReturn(subjectList);

        assertEquals(2,subjectService.findAll().size());

    }

    @Test
    void findByFacultyId() {
        List<Subject> subjectList = new ArrayList<>();
        Subject subject1 = new Subject();
        subject1.setId(1);
        Subject subject2  = new Subject();
        subject2.setId(2);
        subjectList.add(subject1);
        subjectList.add(subject2);

        when(subjectRepository.findByFacultyId(anyInt())).thenReturn(subjectList);

        assertEquals(2,subjectService.findByFacultyId(1).size());
    }
}