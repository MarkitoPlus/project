package com.se.pojo;

import org.apache.taglibs.standard.lang.jstl.test.StaticFunctionTests;

import com.se.mapper.PatientMapper;
import com.se.util.Log;


public class Appointment{
	
	private int 	id;
	
	private String 	patientName;    		
	
	private String  doctorName;				
	
	private String 	date;				
	
	private int 	timeslot;				
	
	private int 	allow;						

	private int     status;
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getTimeslot() {
		return timeslot;
	}

	public void setTimeslot(int timeslot) {
		this.timeslot = timeslot;
	}

	public int getAllow() {
		return allow;
	}

	public void setAllow(int allow) {
		this.allow = allow;
	}

	public Appointment() {
	}
	
	public Appointment(int id, String pName, String dName, String date,
			int ts, int allow, int status) {
		this.id = id;
		this.patientName = pName;
		this.doctorName = dName;
		this.date = date;
		this.timeslot = ts;
		this.allow = allow;
		this.status = status;
	}
	
}