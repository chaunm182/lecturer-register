package com.example.demo.repository;

import com.example.demo.entity.Subject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

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

    @Test
    void findById(){
        Optional<Subject> subjectOptional = subjectRepository.findById(1);
        assertNotNull(subjectOptional.get());
    }

    @Test
    void findById1(){
        Optional<Subject> subjectOptional = subjectRepository.findById(999);
        assertFalse(subjectOptional.isPresent());
    }

    @Test
    void save(){
        Subject subject = new Subject();
        subject.setName("aiwbf");
        subject.setCredit(2);
        subject.setSubjectId("INT1111");

        assertNotNull(subjectRepository.save(subject));
    }

    @Test
    void delete(){
        subjectRepository.deleteById(1);
        assertFalse(subjectRepository.findById(1).isPresent());
    }
}