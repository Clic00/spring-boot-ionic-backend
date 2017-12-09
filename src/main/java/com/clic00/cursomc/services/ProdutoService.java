package com.clic00.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.clic00.cursomc.domain.Categoria;
import com.clic00.cursomc.domain.Produto;
import com.clic00.cursomc.dto.ProdutoDTO;
import com.clic00.cursomc.repositories.CategoriaRepository;
import com.clic00.cursomc.repositories.ProdutoRepository;
import com.clic00.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repo;
	@Autowired
	private CategoriaRepository categoriaRepository;

	public Produto find(Integer id) {
		Produto obj = repo.findOne(id);
		if (obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id:" + id + ", tipo: " + Produto.class.getName());
		}

		return obj;
	}

	public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy,
			String direction) {

		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Categoria> categorias = categoriaRepository.findAll(ids);
		return repo.search(nome, categorias, pageRequest);
//		return repo.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest); // idem ProdutoRepository
	}

	public Produto fromDTO(ProdutoDTO objDTO) {
		return new Produto(objDTO.getId(), objDTO.getNome(), objDTO.getPreco());
	}

}
