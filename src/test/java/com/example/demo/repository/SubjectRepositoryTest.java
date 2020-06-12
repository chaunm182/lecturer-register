package com.example.demo.repository;

import com.example.demo.entity.Subject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class SubjectRepositoryTest {

    @Autowired
    SubjectRepository subjectRepository;

    @Test
    void findByFacultyId() {
        List<Subject> subjects = subjectRepository.findByFacultyId(1);
        assertEquals(4, subjects.size());
    }
}