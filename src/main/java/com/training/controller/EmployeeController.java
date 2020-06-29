package com.training.controller;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.training.dto.EmpDto;
import com.training.exception.validateException;
import com.training.service.EmployeeService;

@RestController
public class EmployeeController extends AbstractEmpClass{

	private final Logger LOG = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private EmployeeService employeeService;
	
	
	@GetMapping("/getAllEmployees")
	public ResponseEntity<Object> getAllUsers() { 
		LOG.info("Getting employees.");
		return new ResponseEntity<>(employeeService.getAllUsers(), HttpStatus.OK);
	}
	
	@PostMapping("/createEmployee")
	public ResponseEntity<Object> createEmployee(@RequestBody EmpDto empDto) {
		LOG.info("saving employee");
//		return employeeService.createEmloyee(empDto);
		ResponseEntity<Object> responseEntity = null;
		try {
			responseEntity = valid(empDto) ? responseBuilder(employeeService.createEmloyee(empDto)) : null;
		} catch (validateException ve) {
			responseEntity = responseBuilder(ve);
		}
		return responseEntity;
	}
	
	@PutMapping("/updateEmployee/{id}")
	public ResponseEntity<Object> updateEmployee(@PathVariable(value = "id") ObjectId empId,@RequestBody EmpDto empDto) {
		LOG.info("updating employee");
//		emp.setEmpId(empId);
		ResponseEntity<Object> responseEntity = null;
		try {
            valid(empId);
            responseEntity = valid(empDto) ? responseBuilder(employeeService.updateEmployee(empId,empDto)) : null;
        } catch (validateException ve) {
            return responseBuilder(ve);
        }
		return responseEntity;
	}
	

	@DeleteMapping("/deleteEmployee/{id}")
	public ResponseEntity<Object> deleteEmployee(@PathVariable(value = "id") ObjectId empId) {
		LOG.info("deleting employee");
		try {
            valid(empId);
            employeeService.deleteEmployee(empId);
        } catch (validateException ve) {
            return responseBuilder(ve);
        }
		return new ResponseEntity<>("Employee deleted from Database", HttpStatus.OK);
	}
}
