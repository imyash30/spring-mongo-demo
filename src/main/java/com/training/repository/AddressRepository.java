package com.training.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.training.model.Address;

public interface AddressRepository extends MongoRepository<Address, String>{

}
