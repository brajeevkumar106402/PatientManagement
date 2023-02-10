package com.patient.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Patient is a Model class which hold addressList and telephoneList
 */
public class Patient {
	
	private Long patient_id;
	private String patientName;
	private String dateOfBirth;
	private String genderCode;
	private List<Address> addressList = new ArrayList<>();	
	private List<Telephone> telephoneList = new ArrayList<>();
	public Long getPatient_id() {
		return patient_id;
	}
	public void setPatient_id(Long patient_id) {
		this.patient_id = patient_id;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getGenderCode() {
		return genderCode;
	}
	public void setGenderCode(String genderCode) {
		this.genderCode = genderCode;
	}
	public List<Address> getAddressList() {
		return addressList;
	}
	public void setAddressList(List<Address> addressList) {
		this.addressList = addressList;
	}
	public List<Telephone> getTelephoneList() {
		return telephoneList;
	}
	public void setTelephoneList(List<Telephone> telephoneList) {
		this.telephoneList = telephoneList;
	}
	
	
	public Patient() {
		super();
		
	}
	public Patient(Long patient_id, String patientName, String dateOfBirth, String genderCode,
			List<Address> addressList, List<Telephone> telephoneList) {
		super();
		this.patient_id = patient_id;
		this.patientName = patientName;
		this.dateOfBirth = dateOfBirth;
		this.genderCode = genderCode;
		this.addressList = addressList;
		this.telephoneList = telephoneList;
	}
	@Override
	public String toString() {
		return "Patient [patient_id=" + patient_id + ", patientName=" + patientName + ", dateOfBirth=" + dateOfBirth
				+ ", genderCode=" + genderCode + ", addressList=" + addressList + ", telephoneList=" + telephoneList
				+ "]";
	}
	
	
	
	
}