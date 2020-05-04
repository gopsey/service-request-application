package com.bvs.servicerequest.entities;

import javax.persistence.Entity;

@Entity
public class Company extends AbstractEntity {

	private String name;
	private String code;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
