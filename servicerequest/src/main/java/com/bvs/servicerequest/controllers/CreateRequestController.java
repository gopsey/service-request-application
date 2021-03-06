package com.bvs.servicerequest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bvs.servicerequest.dto.CreateRequestRequest;
import com.bvs.servicerequest.services.CreateRequestService;

@RestController
@CrossOrigin(origins = "*")
public class CreateRequestController {

	@Autowired
	CreateRequestService createRequestService;

	/*
	 * @param request has product_model, service_type, product_invoice_number,
	 * detailed_complaint, email. Calls createRequest() from CreateRequestService
	 */
	@RequestMapping("/createRequest")
	public Boolean createRequest(@RequestBody CreateRequestRequest request) {
		return createRequestService.createRequest(request);
	}
}
