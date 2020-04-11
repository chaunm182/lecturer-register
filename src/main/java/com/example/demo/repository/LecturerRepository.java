package com.example.demo.repository;

import com.example.demo.entity.Lecturer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LecturerRepository extends JpaRepository<Lecturer,Integer> {
}
