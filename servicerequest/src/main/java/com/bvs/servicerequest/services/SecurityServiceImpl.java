package com.bvs.servicerequest.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService {

	@Autowired
	UserDetailsService userDetailsService;

	@Autowired
	AuthenticationManager authenticationManager;

	private static final Logger LOGGER = LoggerFactory.getLogger(SecurityServiceImpl.class);

	@Override
	public boolean login(String username, String password) {
		UserDetails userDetails = userDetailsService.loadUserByUsername(username);
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, password,
				userDetails.getAuthorities());
		LOGGER.info("Encrypted password: " + password);
		LOGGER.info("Actual password in DB: " + userDetails.getPassword());
		org.springframework.security.crypto.password.PasswordEncoder encoder = new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder();
		LOGGER.info("Is password match? " + encoder.matches(password, userDetails.getPassword()));
		authenticationManager.authenticate(token);
		boolean isAuthenticated = token.isAuthenticated();
		if (isAuthenticated) {
			SecurityContextHolder.getContext().setAuthentication(token);
		}
		return isAuthenticated;
	}

}
