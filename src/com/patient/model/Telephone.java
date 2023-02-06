package com.patient.model;


/**
 * 
 * @author BK106402 This class is used as entity class for holding Telephone
 *         details of Patient
 *
 */
public class Telephone {

	private Long id;
	private String telephoneType;
	private String telephoneNumber;
	private String telephonCountryCode;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTelephoneType() {
		return telephoneType;
	}
	public void setTelephoneType(String telephoneType) {
		this.telephoneType = telephoneType;
	}
	public String getTelephoneNumber() {
		return telephoneNumber;
	}
	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}
	public String getTelephonCountryCode() {
		return telephonCountryCode;
	}
	public void setTelephonCountryCode(String telephonCountryCode) {
		this.telephonCountryCode = telephonCountryCode;
	}
	public Telephone() {
		super();
	}
	public Telephone(Long id, String telephoneType, String telephoneNumber, String telephonCountryCode) {
		super();
		this.id = id;
		this.telephoneType = telephoneType;
		this.telephoneNumber = telephoneNumber;
		this.telephonCountryCode = telephonCountryCode;
	}
	@Override
	public String toString() {
		return "Telephone [id=" + id + ", telephoneType=" + telephoneType + ", telephoneNumber=" + telephoneNumber
				+ ", telephonCountryCode=" + telephonCountryCode + "]";
	}
	
	
	
	

}
