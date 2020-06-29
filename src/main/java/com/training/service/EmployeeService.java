package com.training.service;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.dto.EmpDto;
import com.training.model.Address;
import com.training.model.Employee;
import com.training.repository.AddressRepository;
import com.training.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
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
		List<Address> addressList = emp.getAddressList();
		addressRepository.saveAll(addressList);
		employeeRepository.save(emp);
		return empDto;
	}

	public EmpDto updateEmployee(ObjectId empId, EmpDto empDto) {
		// TODO Auto-generated method stub
		Employee emp = employeeRepository.findByEmpId(empId);
		emp.setName(empDto.getName());
		emp.setActive(true);
		emp.setAge(empDto.getAge());
		emp.setEmail(empDto.getEmail());
		emp.setMobile(empDto.getMobile());
		emp.setCompanyName(empDto.getCompanyName());
		emp.setExperience(empDto.getExperience());
		emp.setSalary(empDto.getSalary());
		emp.setUpdatedDate(new Date());
		addressRepository.deleteAll(emp.getAddressList());
		emp.setAddressList(empDto.getAddressList());
		addressRepository.saveAll(empDto.getAddressList());
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
