package com.clic00.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.clic00.cursomc.domain.Categoria;
import com.clic00.cursomc.domain.Cidade;
import com.clic00.cursomc.domain.Estado;
import com.clic00.cursomc.domain.Produto;
import com.clic00.cursomc.repositories.CategoriaRepository;
import com.clic00.cursomc.repositories.CidadeRepository;
import com.clic00.cursomc.repositories.EstadoRepository;
import com.clic00.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null,"Informática");
		Categoria cat2 = new Categoria(null,"Escritório");
		Categoria cat3 = new Categoria(null,"Expediente");
		
		Produto p1 = new Produto(null, "Computador",2000d);
		Produto p2 = new Produto(null, "Impressora",800.00);
		Produto p3 = new Produto(null, "Mouse",80.00);
		Produto p4 = new Produto(null, "Papel A4", 10.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2,p4));
		cat3.getProdutos().addAll(Arrays.asList(p4));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		p4.getCategorias().addAll(Arrays.asList(cat2,cat3));
		
		categoriaRepository.save(Arrays.asList(cat1,cat2,cat3));
		produtoRepository.save(Arrays.asList(p1,p2,p3,p4));
		
		Estado uf1 = new Estado(null,"Pará");
		Estado uf2 = new Estado(null,"Minas Gerais");
		Estado uf3 = new Estado(null,"São Paulo");
		
		Cidade ue1 = new Cidade(null, "Belém", uf1);
		Cidade ue2 = new Cidade(null,"Uberlândia",uf2);
		Cidade ue3 = new Cidade(null,"Campinas",uf3);
		Cidade ue4 = new Cidade(null,"Irituia",uf1);
		
		uf1.getCidades().addAll(Arrays.asList(ue1,ue4));
		uf2.getCidades().addAll(Arrays.asList(ue2));
		uf3.getCidades().addAll(Arrays.asList(ue3));
		
		estadoRepository.save(Arrays.asList(uf1,uf2,uf3));
		cidadeRepository.save(Arrays.asList(ue1,ue2,ue3,ue4));
		
	}
}
