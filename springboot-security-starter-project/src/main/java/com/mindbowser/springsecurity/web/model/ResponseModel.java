package com.mindbowser.springsecurity.web.model;

import org.springframework.http.HttpStatus;

public class ResponseModel
{

	private String error;
	private Object data;
	private Integer statusCode;
	private String message;

	public String getError()
	{
		return error;
	}

	public void setError(String error)
	{
		this.error = error;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	public static ResponseModel getInstance()
	{
		ResponseModel response = new ResponseModel();
		response.setStatusCode(HttpStatus.OK.value());
		return response;
	}

	public Integer getStatusCode()
	{
		return statusCode;
	}

	public void setStatusCode(Integer statusCode)
	{
		this.statusCode = statusCode;
	}

	public Object getData()
	{
		return data;
	}

	public void setData(Object data)
	{
		this.data = data;
	}

}
