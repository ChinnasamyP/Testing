package com.fa.Entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table(name = "UNIVERSITY")
public class University {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "UNIVERSITY_ID")
	private Long universityId;
	
	@Column(name = "UNIVERSITY_NAME")
	private String universityName;
	
	
	@ManyToMany(fetch = FetchType.LAZY ,cascade = CascadeType.ALL ,mappedBy = "clgUniversity")
	private Set<College> clg=new HashSet<College>();
}
