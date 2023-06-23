package com.se.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import com.se.pojo.Appointment;

public interface AppointmentMapper {

	public List<Appointment> SelectByDoctorId(int doctorId);

	// ???
	public void InsertAppointment(@Param("pName") String pName, @Param("dName") String dName, 
			@Param("date") String date, @Param("time") int time, @Param("status") int status);
}
