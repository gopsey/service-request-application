package com.bvs.servicerequest.services;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bvs.servicerequest.dto.CreateRequestRequest;
import com.bvs.servicerequest.entities.CreateRequest;
import com.bvs.servicerequest.repos.CreateRequestRepository;

@Service
public class CreateRequestServiceImpl implements CreateRequestService {

	@Autowired
	CreateRequestRepository requestRepository;

	@Override
	public CreateRequest createRequest(CreateRequestRequest request) {
		CreateRequest createdRequestResponse = new CreateRequest();
		Date date = new Date();
		Timestamp timestamp = new Timestamp(date.getTime());
		try {
			CreateRequest createRequest = new CreateRequest();
			createRequest.setProduct_model(request.getProduct_model());
			createRequest.setService_type(request.getService_type());
			createRequest.setProduct_invoice_number(request.getProduct_invoice_number());
			createRequest.setDetailed_complaint(request.getDetailed_complaint());
			createRequest.setUser_id(request.getUser_id());
			/*
			 * Setting current status as always "o" for open temporarily. To be changed once
			 * Technician module is implemented
			 */
			createRequest.setCurrent_status('o');
			createRequest.setCreated_time(timestamp);
			createdRequestResponse = requestRepository.save(createRequest);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return createdRequestResponse;
	}

}
