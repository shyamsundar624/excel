package com.excel.shyam.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.excel.shyam.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Integer> {

}
