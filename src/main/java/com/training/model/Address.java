package com.training.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Address")
public class Address {
	
	@Id
	private ObjectId addressId; 
	private String type;
	private String address;
	private String pincode;
	
	
	public String getAddressId() {
		return addressId.toHexString();
	}
	public void setAddressId(ObjectId addressId) {
		this.addressId = addressId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	
	

}
