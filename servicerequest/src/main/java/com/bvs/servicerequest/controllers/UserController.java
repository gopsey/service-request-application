package com.bvs.servicerequest.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bvs.servicerequest.dto.GetProfileRequest;
import com.bvs.servicerequest.dto.GetProfileResponse;
import com.bvs.servicerequest.entities.Company;
import com.bvs.servicerequest.entities.Role;
import com.bvs.servicerequest.entities.User;
import com.bvs.servicerequest.repos.CompanyRepository;
import com.bvs.servicerequest.repos.UserRepository;
import com.bvs.servicerequest.services.SecurityService;

@RestController
@CrossOrigin(origins = "*")
public class UserController {

	@Autowired
	UserRepository userRepository;

	@Autowired
	CompanyRepository companyRepository;

	@Autowired
	SecurityService securityService;

	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	GetProfileResponse response = new GetProfileResponse();

	@RequestMapping("/registerUser")
	public Boolean register(@RequestBody User user) {
		Boolean isRegisterSuccess = false;
//		Password Encoding
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		try {
			userRepository.save(user);
			isRegisterSuccess = true;
		} catch (Exception e) {
			e.printStackTrace();
			isRegisterSuccess = false;
		}
		return isRegisterSuccess;
	}

	@RequestMapping("/userLogin")
	public boolean login(@RequestBody GetProfileRequest request) {
		String requestEmail = request.getEmail().toString();
		String requestPassword = request.getPassword().toString();
		boolean loginResponse = securityService.login(requestEmail, requestPassword);
		if (loginResponse) {
			return loginResponse;
		} else {
			return false;
		}
	}

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
