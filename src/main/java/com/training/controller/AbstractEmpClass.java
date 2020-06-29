package com.training.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.google.gson.Gson;
import com.training.dto.EmpDto;
import com.training.exception.validateException;
import com.training.model.Address;
import com.training.service.EmployeeService;

abstract class AbstractEmpClass {
	
	@Autowired
	EmployeeService employeeService;
	
	protected boolean valid(EmpDto empDto) throws validateException {
		// TODO Auto-generated method stub
//		boolean isValid = false;
		
		List<String> errors = new ArrayList<>();
		if(!isValidName(empDto.getName())) {
			errors.add("Name invalid, only contain lower and upper case");
		}
		if(!isValidAge(empDto.getAge())) {
			errors.add("Age Invalid, minimum 21 age required");
		}
		
		for(Address ad: empDto.getAddressList()) {
			if(!isValidPincode(ad.getPincode()))
				errors.add("Pincode Invalid, only 6 digits allowed");
		}
 		
 		if(errors.size() == 0)
 			return true;
 		else
 			throw new validateException(HttpStatus.BAD_REQUEST,"Not valid data", errors);
	}
	

	private boolean isValidPincode(String pincode) {
		// TODO Auto-generated method stub
		return pincode.matches("([\\d]){6}");
	}


	private boolean isValidAge(int age) {
		// TODO Auto-generated method stub
		if(age >= 21)
			return true;
		else
			return false;
	}


	private boolean isValidName(String name) {
		// TODO Auto-generated method stub
		return name.matches("([a-zA-Z ]){2,16}");
	}
	
	protected void valid(ObjectId empId) throws validateException {
		// TODO Auto-generated method stub
		if (!employeeService.isExistsById(empId)) {
            throw new validateException(HttpStatus.NOT_FOUND,"Invalid UserId",null);
        }
	}


	protected ResponseEntity<Object> responseBuilder(EmpDto empDto) throws validateException {
			return new ResponseEntity<>(empDto, HttpStatus.OK);
	}
	
	protected ResponseEntity<Object> responseBuilder(validateException ve) {
		Gson gson = new Gson();
		return new ResponseEntity<>(gson.toJson(ve), HttpStatus.BAD_REQUEST);
	}

}
