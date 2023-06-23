package com.se.pojo;

import org.apache.taglibs.standard.lang.jstl.test.StaticFunctionTests;

import com.se.mapper.PatientMapper;
import com.se.util.Log;


public class Case{
	
	private int 	id;
	
	private String 	patientName;    		
	
	private String  doctorName;				
	
	private String 	pres;				
	
	private String 	desc;	
	
	public Case() {
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getPres() {
		return pres;
	}

	public void setPres(String pres) {
		this.pres = pres;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Case(int id, String pName, String dName, String pres, String desc) {
		this.id = id;
		this.patientName = pName;
		this.doctorName = dName;
		this.pres = pres;
		this.desc = desc;
	}
	
}