package com.clic00.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clic00.cursomc.domain.Estado;
import com.clic00.cursomc.repositories.EstadoRepository;
import com.clic00.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class EstadoService {

	@Autowired
	private EstadoRepository repo; // 3. serviço acessando o Repositorio...


	public List<Estado> findAll() {
		try {
			return repo.findAllByOrderByNome();
		} catch (ObjectNotFoundException e) {
			throw new ObjectNotFoundException("Objeto não encontrado! Tipo: " + Estado.class.getName());
		}
	}
}
