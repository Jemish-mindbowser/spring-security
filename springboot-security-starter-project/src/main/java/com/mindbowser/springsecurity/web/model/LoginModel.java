package com.mindbowser.springsecurity.web.model;

import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginModel
{
	@NotBlank
	private String username;

	@NotBlank
	private String password;
}
