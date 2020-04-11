package com.example.demo.serviceimpl;

import com.example.demo.entity.Lecturer;
import com.example.demo.repository.LecturerRepository;
import com.example.demo.service.LecturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LecturerServiceImpl implements LecturerService {
    @Autowired
    private LecturerRepository lecturerRepository;
    @Override
    public Lecturer findById(Integer id) {
        Optional<Lecturer> lecturerOptional = lecturerRepository.findById(id);
        return lecturerOptional.orElse(null);
    }
}
