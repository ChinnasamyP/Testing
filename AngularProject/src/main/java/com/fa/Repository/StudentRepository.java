package com.fa.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fa.Entity.Student;



public interface StudentRepository  extends JpaRepository<Student, Long>{

}
