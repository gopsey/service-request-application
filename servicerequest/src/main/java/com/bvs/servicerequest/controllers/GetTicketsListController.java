package com.bvs.servicerequest.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bvs.servicerequest.dto.GetTicketsListResponse;
import com.bvs.servicerequest.entities.Company;
import com.bvs.servicerequest.entities.CreateRequest;
import com.bvs.servicerequest.entities.Role;
import com.bvs.servicerequest.entities.User;
import com.bvs.servicerequest.repos.CompanyRepository;
import com.bvs.servicerequest.repos.CreateRequestRepository;
import com.bvs.servicerequest.repos.UserRepository;

@RestController
@CrossOrigin(origins = "*")
public class GetTicketsListController {

	@Autowired
	CreateRequestRepository createRequestRepository;

	@Autowired
	CompanyRepository companyRepository;

	@Autowired
	UserRepository userRepository;

	Boolean isTechnician = false;

	/*
	 * @param userId contains user ID and passed to get list of tickets raised from
	 * DB. For role == ROLE_USER, only his company requests are fetched. For role ==
	 * ROLE_TECHNICIAN, list of all requests are fetched from DB
	 */
	@RequestMapping("/getCompanyTicketsList")
	public List<GetTicketsListResponse> getCompanyTicketsList(@RequestBody Long userId) {
		try {
			User currentUser = userRepository.findById(userId).get();
			Set<Role> roles = currentUser.getRoles();
			for (Role userRole : roles) {
				String abc = userRole.getName().toString();
				if (abc.equals("ROLE_TECHNICIAN")) {
					isTechnician = true;
				}
			}
			List<CreateRequest> listOfCompanyRequests;
			if (isTechnician) {
				listOfCompanyRequests = createRequestRepository.findAll();
			} else {
				listOfCompanyRequests = createRequestRepository.findByCompanyId(currentUser.getCompany_id());
			}
			List<GetTicketsListResponse> newList = new ArrayList<>();
			for (CreateRequest createRequest : listOfCompanyRequests) {
				GetTicketsListResponse response = new GetTicketsListResponse();
				response.setProduct_model(createRequest.getProduct_model());
				response.setService_type(createRequest.getService_type());
				response.setProduct_invoice_number(createRequest.getProduct_invoice_number());
				response.setDetailed_complaint(createRequest.getDetailed_complaint());
				String createdTime = createRequest.getCreated_time().toString();
				response.setCreated_time((createdTime).substring(0, createdTime.length() - 2));
				response.setCurrent_status(createRequest.getCurrent_status());
				Company companyName = companyRepository.findById(createRequest.getCompanyId()).get();
				response.setCompanyName(companyName.getName().toString());
				newList.add(response);
			}
			return newList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
