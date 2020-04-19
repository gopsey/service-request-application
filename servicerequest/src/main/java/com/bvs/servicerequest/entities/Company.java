package com.bvs.servicerequest.entities;

import javax.persistence.Entity;

@Entity
public class Company extends AbstractEntity {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
