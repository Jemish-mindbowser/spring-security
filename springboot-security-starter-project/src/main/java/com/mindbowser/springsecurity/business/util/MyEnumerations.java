package com.mindbowser.springsecurity.business.util;

import lombok.Getter;

@Getter
public class MyEnumerations {
	
	public enum ERole
	{
		ROLE_USER,
	    ROLE_MODERATOR,
	    ROLE_ADMIN
	}
}
