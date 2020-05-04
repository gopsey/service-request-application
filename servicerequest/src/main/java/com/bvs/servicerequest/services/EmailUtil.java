package com.bvs.servicerequest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.bvs.servicerequest.dto.CreateRequestRequest;
import com.bvs.servicerequest.entities.CreateRequest;
import com.bvs.servicerequest.repos.CompanyRepository;
import com.bvs.servicerequest.repos.UserRepository;

@Service
public class EmailUtil {
	private JavaMailSender javaMailSender;

	@Autowired
	public EmailUtil(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	@Autowired
	UserRepository userRepository;

	@Autowired
	CompanyRepository companyRepository;

	/*
	 * @param request has product_model, service_type, product_invoice_number,
	 * detailed_complaint, email.
	 * 
	 * @param response has product_model, service_type, product_invoice_number,
	 * detailed_complaint, current_status, created_time, userId, companyId.
	 */
	public void sendEmail(CreateRequestRequest request, CreateRequest response) throws MailException {
		SimpleMailMessage mail = new SimpleMailMessage();
		String userName = (userRepository.findById(response.getUserId()).get()).getFirst_name() + " "
				+ (userRepository.findById(response.getUserId()).get()).getLast_name();
		String companyName = (companyRepository.findById(response.getCompanyId()).get()).getName();
		mail.setTo(request.getEmail());
		mail.setFrom("gopalkris96@gmail.com");
		mail.setSubject("Service Request #" + response.getId() + " raised!");
		mail.setText("New Service Request Number #" + response.getId()
				+ " has been raised. Please find the details of the ticket as below\n\nTicket Details\n"
				+ "\nRequested by: " + userName + "\nCompany Name: " + companyName + "\nProduct Model: "
				+ response.getProduct_model() + "\nService Type: " + response.getService_type()
				+ "\nProduct Invoice Number: " + response.getProduct_invoice_number() + "\nDetailed Complaint: "
				+ response.getDetailed_complaint());

		javaMailSender.send(mail);
	}

}
