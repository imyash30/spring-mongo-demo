package com.training.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.training.model.Employee;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String>{

	Employee findByEmpId(ObjectId empId);

	boolean existsByEmpId(ObjectId empId);

}
