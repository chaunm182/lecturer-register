package com.example.demo.repository;

import com.example.demo.entity.Account;
import com.example.demo.entity.Faculty;
import com.example.demo.entity.Lecturer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class LecturerRepositoryTest {

    @Autowired
    LecturerRepository lecturerRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void findAll(){
        List<Lecturer> lecturerList = lecturerRepository.findAll();
        assertEquals(2,lecturerList.size());
    }

    @Test
    void findById(){
        Optional<Lecturer> lecturerOptional = lecturerRepository.findById(1);
        assertTrue(lecturerOptional.isPresent());
    }

    @Test
    void findById1(){
        Optional<Lecturer> lecturerOptional = lecturerRepository.findById(999);
        assertFalse(lecturerOptional.isPresent());
    }

    @Test
    void save(){
        Lecturer lecturer = new Lecturer();
        lecturer.setFirstName("Nguyen Van");
        lecturer.setLastName("A");
        lecturer.setRank("Thạc sĩ");
        lecturer.setAccount(new Account());
        lecturer.setFaculty(new Faculty());

        assertNotNull(lecturerRepository.save(lecturer));
    }

    @Test
    void update(){
        Lecturer lecturer = new Lecturer();
        lecturer.setId(1);
        lecturer.setFirstName("Nguyen Van");
        lecturer.setLastName("A");
        lecturer.setRank("Thạc sĩ");
        lecturer.setAccount(new Account());
        lecturer.setFaculty(new Faculty());

        assertNotNull(lecturerRepository.save(lecturer));
    }

    @Test
    void delete(){
        lecturerRepository.deleteById(1);
        assertFalse(lecturerRepository.findById(1).isPresent());
    }
}