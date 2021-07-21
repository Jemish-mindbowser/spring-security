package com.mindbowser.springsecurity.business.service;

import com.mindbowser.springsecurity.web.model.LoginModel;
import com.mindbowser.springsecurity.web.model.SignupModel;

public interface UserService
{
	Object register(SignupModel signupModel);

	Object loginUser(LoginModel loginModel);
}
