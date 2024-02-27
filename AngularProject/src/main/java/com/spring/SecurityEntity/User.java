package com.spring.SecurityEntity;

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

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "USER")
public class User {
	
	
	
	  public User(String username, String email, String password) {
		    this.username = username;
		    this.email = email;
		    this.password = password;
	 }

	
	
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private Long id;


	  @Column(name = "USERNAME")
	  private String username;

	  @Column(name = "EMAIL")
	  private String email;

	  @Column(name = "PASSWORD")
	  private String password;
	  
	  
	  
	  @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	  @JoinTable(name = "USER_ROLES",
	  joinColumns = @JoinColumn(name="user_id",referencedColumnName = "id"),
	  inverseJoinColumns = @JoinColumn(name="role_id",referencedColumnName = "id"))
	  private Set<Role> roles=new  HashSet<>();

	  

}
