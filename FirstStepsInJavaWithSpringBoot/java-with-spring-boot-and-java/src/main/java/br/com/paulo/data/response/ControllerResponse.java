package br.com.paulo.data.response;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

public class ControllerResponse<T> extends ResponseEntity<T>{
	
	public ControllerResponse(T body, HttpStatusCode status) {
		super(body, status);
		// TODO Auto-generated constructor stub
	}
	public boolean success;
	public String message;
}
