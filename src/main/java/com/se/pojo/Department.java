package com.se.pojo;

public class Department {
	/*
	 * 科室类成员变量
	 * */
	private int 	deptId;			// 科室id
	
	private String 	deptName;		// 科室名

	private String  description;	// 科室介绍

	public int getDeptId() {
		return deptId;
	}

	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Department(int deptId, String deptName, String description) {
		super();
		this.deptId = deptId;
		this.deptName = deptName;
		this.description = description;
	}

	@Override
	public String toString() {
		return "Department [deptId=" + deptId + ", deptName=" + deptName + ", description=" + description + "]";
	}
	
}
