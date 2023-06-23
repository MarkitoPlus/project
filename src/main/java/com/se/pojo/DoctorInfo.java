package com.se.pojo;

/**
 * 医生描述
 * 用于展示在医生介绍和医生个人资料中
 * 
 */
public class DoctorInfo {
	private int doctorId;
	
	private String description;

	public int getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	// ctors
	public DoctorInfo() {
		
	}
	
}
