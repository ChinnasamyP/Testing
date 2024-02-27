package com.fa.Repository; 

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.fa.Entity.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

	
	List<Course> findByTitleContaining(String title,Pageable pageable);
}
