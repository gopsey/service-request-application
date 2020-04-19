package com.bvs.servicerequest.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bvs.servicerequest.dto.GetTicketsListResponse;
import com.bvs.servicerequest.entities.Company;
import com.bvs.servicerequest.entities.CreateRequest;
import com.bvs.servicerequest.repos.CompanyRepository;
import com.bvs.servicerequest.repos.CreateRequestRepository;

@RestController
@CrossOrigin(origins = "*")
public class GetTicketsListController {

	@Autowired
	CreateRequestRepository createRequestRepository;

	@Autowired
	CompanyRepository companyRepository;

	@RequestMapping("/getCompanyTicketsList")
	public List<GetTicketsListResponse> getCompanyTicketsList(@RequestBody Long company_id) {
		try {
			List<CreateRequest> listOfCompanyRequests = createRequestRepository.findByCompanyId(company_id);
			List<GetTicketsListResponse> newList = new ArrayList<>();
			GetTicketsListResponse response = new GetTicketsListResponse();
			for (CreateRequest createRequest : listOfCompanyRequests) {
				response.setProduct_model(createRequest.getProduct_model());
				response.setService_type(createRequest.getService_type());
				response.setProduct_invoice_number(createRequest.getProduct_invoice_number());
				response.setDetailed_complaint(createRequest.getDetailed_complaint());
				response.setCreated_time(createRequest.getCreated_time());
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
