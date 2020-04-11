package com.bvs.servicerequest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bvs.servicerequest.dto.GetProfileRequest;
import com.bvs.servicerequest.dto.GetProfileResponse;
import com.bvs.servicerequest.entities.User;
import com.bvs.servicerequest.repos.UserRepository;

@RestController
@CrossOrigin(origins = "*")
public class UserController {

	@Autowired
	UserRepository userRepository;

	GetProfileResponse response = new GetProfileResponse();

	@RequestMapping("/registerUser")
	public Boolean register(@RequestBody User user) {
		Boolean isRegisterSuccess = false;
		try {
			userRepository.save(user);
			isRegisterSuccess = true;
		} catch (Exception e) {
			e.printStackTrace();
			isRegisterSuccess = false;
		}
		return isRegisterSuccess;
	}

	@RequestMapping("/getProfile")
	public GetProfileResponse login(@RequestBody GetProfileRequest request) {
		String requestEmail = request.getEmail().toString();
		String requestPassword = request.getPassword().toString();
		User user = userRepository.findByEmail(requestEmail);
		if (user != null && user.getPassword().equals(requestPassword)) {
			response.setFirst_name(user.getFirst_name().toString());
			response.setLast_name(user.getLast_name().toString());
			response.setEmail(user.getEmail().toString());
			response.setCompany_name(user.getCompany_name().toString());
			response.setPhone(user.getPhone().toString());
			return response;
		} else {
			return null;
		}
	}
}
