package com.mindbowser.springsecurity.web.controller;

import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.mindbowser.springsecurity.business.service.UserService;
import com.mindbowser.springsecurity.web.model.LoginModel;
import com.mindbowser.springsecurity.web.model.ResponseModel;
import com.mindbowser.springsecurity.web.model.SignupModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class AuthController extends BaseController
{
	private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

	@Autowired
	UserService userService;

	@PostMapping("/auth/register")
	@ApiResponses(value = {@ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 404, message = "Service not found"),
			@ApiResponse(code = 200, message = "Successful retrieval", response = ResponseModel.class)})
	@ApiOperation(value = "Register user.", notes = "This API used to register user.")
	public ResponseEntity<ResponseModel> register(@Valid @RequestBody SignupModel signupModel)
	{
		LOGGER.info("------------ In register [web service] --------------");
		ResponseModel response = ResponseModel.getInstance();
		response.setData(userService.register(signupModel));
		response.setMessage("User registered successfully");
		response.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/auth/signin")
	@ApiResponses(value = {@ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 404, message = "Service not found"),
			@ApiResponse(code = 200, message = "Successful retrieval", response = ResponseModel.class)})
	@ApiOperation(value = "Sign-in user.", notes = "This API used to sign-in and get JWT token.")
	public ResponseEntity<ResponseModel> loginUser(@Valid @RequestBody LoginModel loginModel)
	{
		LOGGER.info("------------ In signin [web service] --------------");
		ResponseModel response = ResponseModel.getInstance();
		response.setData(userService.loginUser(loginModel));
		response.setMessage("User Sign-in successfully");
		response.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
