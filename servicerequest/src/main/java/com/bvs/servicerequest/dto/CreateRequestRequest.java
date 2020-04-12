package com.bvs.servicerequest.dto;

public class CreateRequestRequest {

	private String product_model;
	private String service_type;
	private String product_invoice_number;
	private String detailed_complaint;
	private String email;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
