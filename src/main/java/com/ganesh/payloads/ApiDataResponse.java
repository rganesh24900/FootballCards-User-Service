package com.ganesh.payloads;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ApiDataResponse {
	boolean success;
	String message;
    Object data;
}
