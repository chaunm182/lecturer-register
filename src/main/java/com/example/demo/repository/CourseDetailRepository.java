package com.example.demo.repository;

import com.example.demo.entity.CourseDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseDetailRepository extends JpaRepository<CourseDetail,Integer> {
    List<CourseDetail> findByCourseId(Integer id);
}
