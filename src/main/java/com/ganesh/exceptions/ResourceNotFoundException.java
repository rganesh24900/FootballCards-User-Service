package com.ganesh.exceptions;


public class ResourceNotFoundException extends RuntimeException {
	
	String resource;
	String resourceType;
	int resourceValue;
	public ResourceNotFoundException(String resource ,String resourceType , int resourceValue) {
		super(String.format("The %s of %s with value %d is not found",resource,resourceType,resourceValue ));
		this.resource=resource;
		this.resourceType=resourceType;
		this.resourceValue=resourceValue;
	}

}
