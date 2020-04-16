package com.bvs.servicerequest.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bvs.servicerequest.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
