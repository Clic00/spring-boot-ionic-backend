package com.clic00.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clic00.cursomc.domain.Categoria;
import com.clic00.cursomc.repositories.CategoriaRepository;
import com.clic00.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo; // 3. serviço acessando o Repositorio...

	public Categoria buscar(Integer id) {
		Categoria obj = repo.findOne(id);
		if (obj == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! Id:" + id + ", tipo: " + Categoria.class.getName());
		}
		return obj;
	}
}
