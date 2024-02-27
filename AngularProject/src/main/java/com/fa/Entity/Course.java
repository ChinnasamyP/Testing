package com.fa.Entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Course {

	@Id
	@SequenceGenerator(name = "Course_seq",sequenceName = "Course_seq",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "Course_seq")
	private Long courseId;
	
	private String title;
	
	private Integer credit;


	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
		name="student_course_map",
		joinColumns = @JoinColumn(
				name="course_id",
				referencedColumnName = "courseId"
				),
		inverseJoinColumns = @JoinColumn(
				name="student_id",
				referencedColumnName = "studentId")
	)
	private List<Student> students;
	
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	@JoinColumn(name = "teacher_id",referencedColumnName = "teacherId")
	private Teacher teacher;
	
	

	
	public void addStudents(Student student) {
		if (students==null) students=new ArrayList<Student>();
		students.add(student);
			
		
	}
	

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getCredit() {
		return credit;
	}

	public void setCredit(Integer credit) {
		this.credit = credit;
	}
	
	@Override
	public String toString() {
		return "Course [courseId=" + courseId + ", title=" + title + ", credit=" + credit + "]";
	}
	
	
}
