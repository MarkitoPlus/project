package com.se.mapper;

import java.util.List;

import com.se.pojo.Department;

public interface DepartmentMapper {
	/*
	 * 查询所有科室
	 * */
	public List<Department> SelectDepartments();
	
	/*
	 * 根据科室名查询科室
	 * */
	public Department SelectDeptByName(String departmentName);
}
