package com.clic00.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clic00.cursomc.domain.Pedido;
import com.clic00.cursomc.repositories.PedidoRepository;
import com.clic00.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;

	public Pedido buscar(Integer id) {
		Pedido obj = repo.findOne(id);
		if (obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id:" + id 
												+ ", tipo: " + Pedido.class.getName());
		}
		
		return obj;
	}

}
