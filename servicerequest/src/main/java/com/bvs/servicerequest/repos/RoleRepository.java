package com.bvs.servicerequest.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bvs.servicerequest.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(String name);
}
