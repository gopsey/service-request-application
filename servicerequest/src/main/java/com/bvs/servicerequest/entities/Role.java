package com.bvs.servicerequest.entities;

import javax.persistence.Entity;

import org.springframework.security.core.GrantedAuthority;

@Entity
public class Role extends AbstractEntity implements GrantedAuthority {

	private String name;
	
	public Role() {

	}

	public Role(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getAuthority() {
		return name;
	}

}
