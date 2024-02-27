package com.fa.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table (name="angularform")
public class AngularEntity {

		@Id
		@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "angularform_seq")
		@SequenceGenerator(name = "angularform_seq",sequenceName = "angularform_seq", allocationSize = 1)
		private long id;
		
		@Column (name="Email")
		private String email;
		
		@Column (name="user")
		private String user;
		
		@Column (name="createdBy")
		private String createdBy;
		
		@Column (name="createdDate")
		private String createdDate;
		
		@Column (name="Password")
		private String password;
		
		//6
				@Column (name="AccountNumber")
				private String accountNumber;
				
				//7
				@Column(name="Branch")
				private String branch;
				
				//8
				@Column (name="Zone")
				private String zone;
				
				//9
				@Column(name="District")
				private String district;
				
				//10
				@Column (name="State")
				private String state;
				
				//12
				@Column(name="Country")
				private String country;
				
				//13
				@Column (name="IFSCNO")
				private String ifscNO;
				
				//14
				@Column (name="MICRNO")
				private String micrNO;
				
				//15
				@Column (name="AccountType")
				private String accountType;
				
				//16
				@Column (name="CardNumber")
				private String cardNumber;
				
				//17
				@Column (name="CardExpDate")
				private String cardExpDate;
				
				//18
				@Column (name="CardType")
				private String cardType;
				
				//19
				@Column (name="CardLimitPerDay")
				private String cardLimit;
				
				//20
				@Column (name="Status")
				private String status;
				
	
		
		//Getter And Setter Starts...
		
		
		
		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public String getUser() {
			return user;
		}

		public void setUser(String user) {
			this.user = user;
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

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getAccountNumber() {
			return accountNumber;
		}

		public void setAccountNumber(String accountNumber) {
			this.accountNumber = accountNumber;
		}

		public String getBranch() {
			return branch;
		}

		public void setBranch(String branch) {
			this.branch = branch;
		}

		public String getZone() {
			return zone;
		}

		public void setZone(String zone) {
			this.zone = zone;
		}

		public String getDistrict() {
			return district;
		}

		public void setDistrict(String district) {
			this.district = district;
		}

		public String getState() {
			return state;
		}

		public void setState(String state) {
			this.state = state;
		}

		public String getCountry() {
			return country;
		}

		public void setCountry(String country) {
			this.country = country;
		}

		public String getIfscNO() {
			return ifscNO;
		}

		public void setIfscNO(String ifscNO) {
			this.ifscNO = ifscNO;
		}

		public String getMicrNO() {
			return micrNO;
		}

		public void setMicrNO(String micrNO) {
			this.micrNO = micrNO;
		}

		public String getAccountType() {
			return accountType;
		}

		public void setAccountType(String accountType) {
			this.accountType = accountType;
		}

		public String getCardNumber() {
			return cardNumber;
		}

		public void setCardNumber(String cardNumber) {
			this.cardNumber = cardNumber;
		}

		public String getCardExpDate() {
			return cardExpDate;
		}

		public void setCardExpDate(String cardExpDate) {
			this.cardExpDate = cardExpDate;
		}

		public String getCardType() {
			return cardType;
		}

		public void setCardType(String cardType) {
			this.cardType = cardType;
		}

		public String getCardLimit() {
			return cardLimit;
		}

		public void setCardLimit(String cardLimit) {
			this.cardLimit = cardLimit;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		
}
