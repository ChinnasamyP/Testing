package com.fa.Entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseMaterial {
	
	
	
	@Id
	@SequenceGenerator(name = "CourseMaterial_seq",sequenceName = "CourseMaterial_seq",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "CourseMaterial_seq")
	private Long courseMaterialId;
	
	private String url;
	
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "course_id",referencedColumnName = "courseId")
	private Course course;


	public Long getCourseMaterialId() {
		return courseMaterialId;
	}


	public void setCourseMaterialId(Long courseMaterialId) {
		courseMaterialId = courseMaterialId;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public Course getCourse() {
		return course;
	}


	public void setCourse(Course course) {
		this.course = course;
	}
	
	

}
