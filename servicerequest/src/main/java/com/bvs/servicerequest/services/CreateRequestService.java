package com.bvs.servicerequest.services;

import com.bvs.servicerequest.dto.CreateRequestRequest;
import com.bvs.servicerequest.entities.CreateRequest;

public interface CreateRequestService {
	
	public CreateRequest createRequest(CreateRequestRequest request);

}
