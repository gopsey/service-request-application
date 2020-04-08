package com.bvs.servicerequest.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bvs.servicerequest.entities.Request;

public interface RequestRepository extends JpaRepository<Request, Long> {

}
