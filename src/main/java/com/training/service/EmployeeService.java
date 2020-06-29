package com.training.service;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.dto.EmpDto;
import com.training.model.Employee;
import com.training.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	ModelMapper modelMapper;

	public List<EmpDto> getAllUsers() {
		// TODO Auto-generated method stub
		List<Employee> empList = employeeRepository.findAll();
		List<EmpDto> empDtoList = modelMapper.map(empList, new TypeToken<List<EmpDto>>() {}.getType());
		return empDtoList;
	}

	public EmpDto createEmloyee(EmpDto empDto) {
		// TODO Auto-generated method stub
		Employee emp = modelMapper.map(empDto, new TypeToken<Employee>() {}.getType());
		emp.setCreatedDate(new Date());
		emp.setUpdatedDate(new Date());
		employeeRepository.save(emp);
		return empDto;
	}

	public EmpDto updateEmployee(ObjectId empId, EmpDto empDto) {
		// TODO Auto-generated method stub
		Employee emp = modelMapper.map(empDto, new TypeToken<Employee>() {}.getType());
		emp.setEmpId(empId);
		emp.setCreatedDate(new Date());
		emp.setUpdatedDate(new Date());
		employeeRepository.save(emp);
		return empDto;
	}

	public Object getEmpById(ObjectId empId) {
		// TODO Auto-generated method stub
		return null;
	}

	public void deleteEmployee(ObjectId empById) {
		// TODO Auto-generated method stub
		Employee emp = employeeRepository.findByEmpId(empById);
		employeeRepository.delete(emp);
		
	}

	public boolean isExistsById(ObjectId empId) {
		// TODO Auto-generated method stub
		return employeeRepository.existsByEmpId(empId);
	}

}
