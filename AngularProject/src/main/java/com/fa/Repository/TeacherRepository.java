package com.fa.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fa.Entity.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

	
	
}
