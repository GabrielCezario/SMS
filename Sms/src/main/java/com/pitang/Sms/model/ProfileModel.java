package com.pitang.Sms.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

@Entity
@Table(name = "user_profilers")
public class ProfileModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "phoneNumber")
	@Size(max = 50)
	private String phoneNumber;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "dateOfBirth")
	private Date dateOfBirth;
	
	@Column(name = "adress")
	@Size(max = 300)
	private String adress;
	
	@Column(name = "country")
	@Size(max = 50)
	private String country;

	@Column(name = "state")
	@Size(max = 50)
	private String state;
	
	@Column(name = "city")
	@Size(max = 50)
	private String city;
	
	@Column(name = "street")
	@Size(max = 50)
	private String street;
	
	@Column(name = "zipCode")
	@Size(max = 50)
	private String zipCode;
	
	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "userModel_id", nullable = false)
	private UserModel userModel;
	
	public ProfileModel() {
		
	}
	
	public ProfileModel(String phoneNumber, Date dateOfBirth, String adress, String country, String state, String city, String street, String zipCode) {
		this.phoneNumber = phoneNumber;
		this.dateOfBirth = dateOfBirth;
		this.adress = adress;
		this.country = country;
		this.state = state;
		this.city = city;
		this.street = street;
		this.zipCode = zipCode;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public UserModel getUserModel() {
		return userModel;
	}

	public void setUserModel(UserModel userModel) {
		this.userModel = userModel;
	}
	
}
