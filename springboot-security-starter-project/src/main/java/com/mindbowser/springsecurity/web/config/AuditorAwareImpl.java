package com.mindbowser.springsecurity.web.config;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import com.mindbowser.springsecurity.business.util.AuthenticationUtils;
import com.mindbowser.springsecurity.business.util.Validator;

public class AuditorAwareImpl implements AuditorAware<String>
{

	@Autowired
	AuthenticationUtils authenticationUtils;

	@Override
	public Optional<String> getCurrentAuditor()
	{
		String userEmail = authenticationUtils.getLoggedInUserNameFromSecurity();
		if (!Validator.isEmpty(userEmail))
		{
			return Optional.of(userEmail);
		}
		else
		{
			return Optional.of("anonymousUser");
		}
	}

}
