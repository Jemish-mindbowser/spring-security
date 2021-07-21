package com.mindbowser.springsecurity.business.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.mindbowser.springsecurity.business.exception.ResourceAlreadyExistsException;
import com.mindbowser.springsecurity.business.exception.ResourceNotFoundException;
import com.mindbowser.springsecurity.business.util.MyEnumerations.ERole;
import com.mindbowser.springsecurity.persistance.entity.domain.Role;
import com.mindbowser.springsecurity.persistance.entity.domain.User;
import com.mindbowser.springsecurity.persistance.repository.RoleRepository;
import com.mindbowser.springsecurity.persistance.repository.UserRepository;
import com.mindbowser.springsecurity.web.jwt.JwtUtils;
import com.mindbowser.springsecurity.web.model.LoginModel;
import com.mindbowser.springsecurity.web.model.SignupModel;

@Service
public class UserServiceImpl implements UserService
{
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@Override
	public Object register(SignupModel signupModel)
	{
		if (Boolean.TRUE.equals(userRepository.existsByUsername(signupModel.getUsername())))
			throw new ResourceAlreadyExistsException("User already exist with username :: " + signupModel.getUsername());

		if (Boolean.TRUE.equals(userRepository.existsByEmail(signupModel.getEmail())))
			throw new ResourceAlreadyExistsException("User already exist with email :: " + signupModel.getUsername());

		registerUserMethod(signupModel);

		return "User registered successfully!";
	}

	void registerUserMethod(SignupModel signupModel)
	{
		Set<Role> roles = new HashSet<>();

		User user = new User();
		user.setUsername(signupModel.getUsername());
		user.setEmail(signupModel.getEmail());
		user.setPassword(encoder.encode(signupModel.getPassword()));
		user.setIsDeleted(false);

		Role userRole = roleRepository.findByName(ERole.ROLE_USER)
				.orElseThrow(() -> new ResourceNotFoundException("Error: Role is not found."));
		roles.add(userRole);

		user.setRoles(roles);

		userRepository.save(user);
	}

	@Override
	public Object loginUser(@Valid LoginModel loginModel)
	{

		Map<String, Object> data = new HashMap<>();

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginModel.getUsername(), loginModel.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());
		data.put("jwt", jwt);
		data.put("id", userDetails.getId());
		data.put("username", userDetails.getUsername());
		data.put("email", userDetails.getEmail());
		data.put("roles", roles);

		return data;
	}

}
