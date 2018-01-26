package com.clic00.cursomc.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.clic00.cursomc.domain.Cliente;
import com.clic00.cursomc.repositories.ClienteRepository;
import com.clic00.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class AuthService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private BCryptPasswordEncoder bCrypt;

	@Autowired
	private EmailService emailService;
	
	private Random rand = new Random();

	public void sendNewPassword(String email) {

		Cliente cliente = clienteRepository.findByEmail(email);
		if (cliente == null) {
			throw new ObjectNotFoundException("Email não encontrado!");
		}

		String newPass = newPassword();
		cliente.setSenha(bCrypt.encode(newPass));

		clienteRepository.save(cliente);

		emailService.sendNewPasswordEmail(cliente, newPass);
	}

	private String newPassword() {
		char[] vet = new char[10];
		
		int i = 0;		
		for (char v : vet) {
			v = randomChar();
			vet[i] = v;
			i += 1;			
		}
		
		return new String(vet);
	}

	private char randomChar() {
		
		int opt = rand.nextInt(3);
		if(opt == 0) {  // gera um dígito
			
			return (char) (rand.nextInt(10) + 48);
		}
		else if(opt == 1){ //gera letra maiuscula
			return (char) (rand.nextInt(26) + 65);
		}
		else { // gera letra minuscula
			return (char) (rand.nextInt(26) + 97);
		}

	}
	
}
