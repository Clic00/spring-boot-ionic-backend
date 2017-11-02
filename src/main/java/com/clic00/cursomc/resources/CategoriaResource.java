package com.clic00.cursomc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.clic00.cursomc.domain.Categoria;
import com.clic00.cursomc.services.CategoriaService;

@RestController
@RequestMapping(value="/categorias") //end-point "categorias"
public class CategoriaResource {
	
	@Autowired
	private CategoriaService service; // 2. acessando o serviço...
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {  // 1 .aqui objeto Controlador REST
		
		Categoria obj = service.buscar(id);
		return ResponseEntity.ok().body(obj);			
	}
	
}
