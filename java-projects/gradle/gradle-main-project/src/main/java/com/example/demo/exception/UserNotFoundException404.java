package com.example.demo.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "User is not found.")
public class UserNotFoundException404 extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 631839694434306482L;

}
