package com.bvs.servicerequest.entities;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "request")
public class CreateRequest extends AbstractEntity {

	private String product_model;
	private String service_type;
	private String product_invoice_number;
	private String detailed_complaint;
	private char current_status;
	private Timestamp created_time;
	@Column(name = "user_id")
	private Long userId;
	@Column(name = "company_id")
	private Long companyId;

	public String getProduct_model() {
		return product_model;
	}

	public void setProduct_model(String product_model) {
		this.product_model = product_model;
	}

	public String getService_type() {
		return service_type;
	}

	public void setService_type(String service_type) {
		this.service_type = service_type;
	}

	public String getProduct_invoice_number() {
		return product_invoice_number;
	}

	public void setProduct_invoice_number(String product_invoice_number) {
		this.product_invoice_number = product_invoice_number;
	}

	public String getDetailed_complaint() {
		return detailed_complaint;
	}

	public void setDetailed_complaint(String detailed_complaint) {
		this.detailed_complaint = detailed_complaint;
	}

	public char getCurrent_status() {
		return current_status;
	}

	public void setCurrent_status(char current_status) {
		this.current_status = current_status;
	}

	public Timestamp getCreated_time() {
		return created_time;
	}

	public void setCreated_time(Timestamp created_time) {
		this.created_time = created_time;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

}
