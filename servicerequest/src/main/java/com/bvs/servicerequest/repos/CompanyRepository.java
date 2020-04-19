package com.bvs.servicerequest.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bvs.servicerequest.entities.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {

}
