package com.bvs.servicerequest.services;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;

import com.bvs.servicerequest.dto.CreateRequestRequest;
import com.bvs.servicerequest.entities.CreateRequest;
import com.bvs.servicerequest.entities.User;
import com.bvs.servicerequest.repos.CreateRequestRepository;
import com.bvs.servicerequest.repos.UserRepository;

@Service
public class CreateRequestServiceImpl implements CreateRequestService {

	@Autowired
	CreateRequestRepository requestRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	EmailUtil emailUtil;

	/*
	 * @param request has product_model, service_type, product_invoice_number,
	 * detailed_complaint, email. Saves data in request table in DB
	 */
	@Override
	public Boolean createRequest(CreateRequestRequest request) {
		CreateRequest createdRequestResponse = new CreateRequest();
		CreateRequest createRequest = new CreateRequest();
		Date date = new Date();
		Timestamp timestamp = new Timestamp(date.getTime());
		User currentUser = userRepository.findByEmail(request.getEmail());
		createRequest.setProduct_model(request.getProduct_model());
		createRequest.setService_type(request.getService_type());
		createRequest.setProduct_invoice_number(request.getProduct_invoice_number());
		createRequest.setDetailed_complaint(request.getDetailed_complaint());
		createRequest.setUserId(currentUser.getId());
		createRequest.setCompanyId(currentUser.getCompany_id());
		/*
		 * Setting current status as always "o" for open temporarily. To be changed once
		 * Technician module is implemented
		 */
		createRequest.setCurrent_status('o');
		createRequest.setCreated_time(timestamp);
		createdRequestResponse = requestRepository.save(createRequest);
//		Sending email with requested ticket details
		try {
			emailUtil.sendEmail(request, createdRequestResponse);
		} catch (MailException e) {
			e.printStackTrace();
		}

		if (createdRequestResponse != null) {
			return true;
		} else {
			return false;
		}
	}

}
