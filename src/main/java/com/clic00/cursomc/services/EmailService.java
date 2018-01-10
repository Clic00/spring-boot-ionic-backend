package com.clic00.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.clic00.cursomc.domain.Pedido;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Pedido obj);
	void sendEmail(SimpleMailMessage msg);

}
