package com.mindbowser.springsecurity.business.exception;

public class RestServiceException extends RuntimeException
{
	private static final long serialVersionUID = 1L;

	public RestServiceException(String message)
	{
		super(message);
	}
}
