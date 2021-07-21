package com.mindbowser.springsecurity.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mindbowser.springsecurity.web.model.ResponseModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class TestController extends BaseController
{

	private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);

	@GetMapping("/test/all")
	@ApiResponses(value = {@ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 404, message = "Service not found"),
			@ApiResponse(code = 200, message = "Successful retrieval", response = ResponseModel.class)})
	@ApiOperation(value = "public endpoint.", notes = "This is public endpoint, doesnt requierd authentication.")
	public String checkAllAccess()
	{
		LOGGER.info("------------ In allAccess [web service] --------------");
		return "Public Content.";
	}

	@GetMapping("/test/user-access")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	@ApiResponses(value = {@ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 404, message = "Service not found"),
			@ApiResponse(code = 200, message = "Successful retrieval", response = ResponseModel.class)})
	@ApiOperation(value = "user endpoint.", notes = "This is user endpoint,any roled user can access it.")
	public String checkUserAccess()
	{
		LOGGER.info("------------ In checkUserAccess [web service] --------------");
		return "Hello authenticated user.";
	}

	@GetMapping("/test/moderator-access")
	@PreAuthorize("hasRole('MODERATOR')")
	@ApiResponses(value = {@ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 404, message = "Service not found"),
			@ApiResponse(code = 200, message = "Successful retrieval", response = ResponseModel.class)})
	@ApiOperation(value = "moderator endpoint.", notes = "This is moderator only endpoint,only user with moderator role can access it.")
	public String checkModeratorAccess()
	{
		LOGGER.info("------------ In checkModeratorAccess [web service] --------------");
		return "Hello Moderator user.";
	}

	@GetMapping("/test/admin-access")
	@PreAuthorize("hasRole('ADMIN')")
	@ApiResponses(value = {@ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 404, message = "Service not found"),
			@ApiResponse(code = 200, message = "Successful retrieval", response = ResponseModel.class)})
	@ApiOperation(value = "admin only endpoint.", notes = "This is admin only endpoint,only user with admin role can access it.")
	public String checkAdminAccess()
	{
		LOGGER.info("------------ In checkAdminAccess [web service] --------------");
		return "Hello Admin user.";
	}
}
