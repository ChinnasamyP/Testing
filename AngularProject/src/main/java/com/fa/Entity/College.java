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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "COLLEGE")
public class College {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CLG_ID")
	private Long  clgId;
	
	
	@Column(name = "CLG_NAME")
	private String clgName;
	
	
	@ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinTable(name = "COLLEGE_UNIVERSITY_MAPPING",
	joinColumns = { @JoinColumn(name="UNIVERSITY_ID")},
	inverseJoinColumns = {@JoinColumn(name="COLLEGE_ID")})
	private Set<University> clgUniversity=new HashSet<>();
	
	
}
