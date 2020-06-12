package com.example.demo.serviceimpl;

import com.example.demo.entity.Lecturer;
import com.example.demo.repository.LecturerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

class LecturerServiceImplTest {

    @Mock
    LecturerRepository lecturerRepository;

    @InjectMocks
    LecturerServiceImpl lecturerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void findByIdWhenLecturerOptionalIsPresent() {
        Lecturer lecturer = new Lecturer();
        lecturer.setId(1);
        Optional<Lecturer> lecturerOptional = Optional.of(lecturer);

        when(lecturerRepository.findById(anyInt())).thenReturn(lecturerOptional);

        assertEquals(lecturerOptional.get(),lecturerService.findById(anyInt()));
    }

    @Test
    void findByIdWhenLecturerOptionalIsNotPresent() {
        Optional<Lecturer> lecturerOptional = Optional.empty();

        when(lecturerRepository.findById(anyInt())).thenReturn(lecturerOptional);

        assertNull(lecturerService.findById(anyInt()));
    }
}