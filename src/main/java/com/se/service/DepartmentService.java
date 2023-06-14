package com.se.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.alibaba.fastjson.JSON;
import com.se.exception.DeptException;
import com.se.exception.ErrorType;
import com.se.mapper.DepartmentMapper;
import com.se.pojo.Department;
import com.se.util.Log;
import com.se.util.SqlSessionFactoryUtils;

public class DepartmentService {
	/*
	 * 静态类
	 * */
	private DepartmentService() {
		
	}
	
	/*
	 * 借助SqlSessionFactoryUtils静态方法获取sqlSessionFactory对象，用于创建sqlSession
	 * */
	private static SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();

	/*
	 * 调用数据库获取所有科室信息
	 * */
	public static List<Department> GetDepts() throws DeptException{
		SqlSession sqlSession = factory.openSession();
		
		DepartmentMapper departmentMapper = sqlSession.getMapper(DepartmentMapper.class);
		
		List<Department> departments = departmentMapper.SelectDepartments();
		
		sqlSession.close();
				
		return departments;
	}
		
	/*
	 * 根据科室名字获取科室
	 * */
	public static Department GetDeptByName(String departmentName) throws DeptException{
		SqlSession sqlSession = factory.openSession();
		
		DepartmentMapper departmentMapper = sqlSession.getMapper(DepartmentMapper.class);
	
		Department department = departmentMapper.SelectDeptByName(departmentName);
		
		sqlSession.close();
		
		if(department == null)
			throw new DeptException(ErrorType.NonexistentDeptName, "DepartmentService.GetDepartment");
		
		
		return department;
	}
	
}
