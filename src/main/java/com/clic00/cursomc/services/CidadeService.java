package com.clic00.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clic00.cursomc.domain.Cidade;
import com.clic00.cursomc.repositories.CidadeRepository;
import com.clic00.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository repo;

	public List<Cidade> findByEstado(Integer estado_id) {
		try {
			return repo.findCidades(estado_id);
		} catch (ObjectNotFoundException e) {
			throw new ObjectNotFoundException("Objeto não encontrado! Tipo: " + Cidade.class.getName());
		}
	}
}
