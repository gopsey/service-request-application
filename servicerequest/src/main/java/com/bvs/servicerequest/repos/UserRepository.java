package com.bvs.servicerequest.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bvs.servicerequest.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);

}
