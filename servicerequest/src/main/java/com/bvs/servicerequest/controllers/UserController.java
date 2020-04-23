package com.bvs.servicerequest.controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bvs.servicerequest.dto.GetProfileRequest;
import com.bvs.servicerequest.dto.GetProfileResponse;
import com.bvs.servicerequest.dto.JwtResponse;
import com.bvs.servicerequest.dto.RegisterRequest;
import com.bvs.servicerequest.entities.Company;
import com.bvs.servicerequest.entities.Role;
import com.bvs.servicerequest.entities.User;
import com.bvs.servicerequest.repos.CompanyRepository;
import com.bvs.servicerequest.repos.RoleRepository;
import com.bvs.servicerequest.repos.UserRepository;
import com.bvs.servicerequest.security.jwt.JwtUtils;
import com.bvs.servicerequest.services.SecurityService;
import com.bvs.servicerequest.services.UserDetailsImpl;

@RestController
@CrossOrigin(origins = "*")
public class UserController {

	@Autowired
	UserRepository userRepository;

	@Autowired
	CompanyRepository companyRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	SecurityService securityService;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	GetProfileResponse response = new GetProfileResponse();

	@RequestMapping("/registerUser")
	public Boolean register(@RequestBody RegisterRequest signUpRequest) {
		User user = new User(signUpRequest.getEmail(), encoder.encode(signUpRequest.getPassword()));
		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();
		if (strRoles == null) {
			Role userRole = roleRepository.findByName("ROLE_USER")
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "technician":
					Role adminRole = roleRepository.findByName("ROLE_TECHNICIAN")
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;
				default:
					Role userRole = roleRepository.findByName("ROLE_USER")
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);
		user.setCompany_id(signUpRequest.getCompany_id());
		user.setFirst_name(signUpRequest.getFirst_name());
		user.setLast_name(signUpRequest.getLast_name());
		user.setPhone(signUpRequest.getPhone());
		userRepository.save(user);
		return true;
	}

	@RequestMapping("/userLogin")
	public ResponseEntity<?> login(@RequestBody GetProfileRequest request) {

		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), roles));
	}

//	@PreAuthorize("hasRole('USER') or hasRole('TECHNICIAN')")
	@RequestMapping("/getProfile")
	public GetProfileResponse getProfile(@RequestBody String userEmail) {
		String requestEmail = userEmail;
		try {
			User user = userRepository.findByEmail(requestEmail);
			if (user != null) {
				response.setFirst_name(user.getFirst_name().toString());
				response.setLast_name(user.getLast_name().toString());
				response.setEmail(user.getEmail().toString());
				response.setPhone(user.getPhone().toString());
				// Get company name
				Long userCompanyId = user.getCompany_id();
				Company companyDetails = companyRepository.findById(userCompanyId).get();
				response.setCompany_name(companyDetails.getName().toString());
				response.setId(user.getId());
				Set<Role> userRoles = user.getRoles();
				List<String> list = new ArrayList<>();
				for (Role eachRole : userRoles) {
					list.add(eachRole.getName().toString());
				}
				response.setRoles(list);
				return response;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
