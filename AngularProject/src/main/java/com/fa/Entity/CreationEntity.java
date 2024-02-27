package com.fa.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table (name="creationform")
public class CreationEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "creationform_seq")
	@SequenceGenerator(name = "creationform_seq",sequenceName = "creationform_seq", allocationSize = 1)
	private long id;
	
	
	@Column (name="User")
	private String user;
	
	@Column (name="Password")
	private String password;
	
	@Column (name="CreatedBy")
	private String createdBy;
	
	
	@Column (name="CreatedDate")
	private String createdDate;


	public String getUser() {
		return user;
	}


	public void setUser(String user) {
		this.user = user;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getCreatedBy() {
		return createdBy;
	}


	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}


	public String getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	
	

}
