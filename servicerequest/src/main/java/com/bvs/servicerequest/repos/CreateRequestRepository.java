package com.bvs.servicerequest.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bvs.servicerequest.entities.CreateRequest;

public interface CreateRequestRepository extends JpaRepository<CreateRequest, Long> {

}
