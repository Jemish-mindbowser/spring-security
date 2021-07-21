package com.mindbowser.springsecurity.business.util;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import com.mindbowser.springsecurity.persistance.entity.domain.User;
import com.mindbowser.springsecurity.persistance.repository.UserRepository;

@Component("authenticationUtils")
public class AuthenticationUtils
{
	private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationUtils.class);

	@Autowired
	private UserRepository userRepository;

	public User getLoggedInUser()
	{
		Optional<User> user = null;
		if (Validator.isEmpty(SecurityContextHolder.getContext().getAuthentication()))
		{
			user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
			if (user.isPresent())
			{
				return user.get();
			}
		}
		else
		{
			LOGGER.warn("No logged user found");
		}

		return null;
	}

	public String getLoggedInUserNameFromSecurity()
	{
		if (SecurityContextHolder.getContext().getAuthentication() != null)
			return SecurityContextHolder.getContext().getAuthentication().getName();
		else
			return null;
	}
}
