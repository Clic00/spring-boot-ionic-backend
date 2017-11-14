package com.clic00.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.clic00.cursomc.domain.Categoria;
import com.clic00.cursomc.domain.Cidade;
import com.clic00.cursomc.domain.Cliente;
import com.clic00.cursomc.domain.Endereco;
import com.clic00.cursomc.domain.Estado;
import com.clic00.cursomc.domain.Produto;
import com.clic00.cursomc.domain.enums.TipoCliente;
import com.clic00.cursomc.repositories.CategoriaRepository;
import com.clic00.cursomc.repositories.CidadeRepository;
import com.clic00.cursomc.repositories.ClienteRepository;
import com.clic00.cursomc.repositories.EnderecoRepository;
import com.clic00.cursomc.repositories.EstadoRepository;
import com.clic00.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		Categoria cat3 = new Categoria(null, "Expediente");

		Produto p1 = new Produto(null, "Computador", 2000d);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		Produto p4 = new Produto(null, "Papel A4", 10.00);

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2, p4));
		cat3.getProdutos().addAll(Arrays.asList(p4));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		p4.getCategorias().addAll(Arrays.asList(cat2, cat3));

		categoriaRepository.save(Arrays.asList(cat1, cat2, cat3));
		produtoRepository.save(Arrays.asList(p1, p2, p3, p4));

		Estado uf1 = new Estado(null, "Pará");
		Estado uf2 = new Estado(null, "Minas Gerais");
		Estado uf3 = new Estado(null, "São Paulo");

		Cidade ue1 = new Cidade(null, "Belém", uf1);
		Cidade ue2 = new Cidade(null, "Uberlândia", uf2);
		Cidade ue3 = new Cidade(null, "Campinas", uf3);
		Cidade ue4 = new Cidade(null, "Irituia", uf1);

		uf1.getCidades().addAll(Arrays.asList(ue1, ue4));
		uf2.getCidades().addAll(Arrays.asList(ue2));
		uf3.getCidades().addAll(Arrays.asList(ue3));

		estadoRepository.save(Arrays.asList(uf1, uf2, uf3));
		cidadeRepository.save(Arrays.asList(ue1, ue2, ue3, ue4));

		Cliente cli1 = new Cliente(null, "Maria Meire", "meire@indra.com", "243.354.005-56", TipoCliente.PESSOA_FISICA);
		Cliente cli2 = new Cliente(null, "Rosana Kalil", "tialinda@gmail.com", "132.143.655-90",
				TipoCliente.PESSOA_JURIDICA);

		cli1.getTelefones().addAll(Arrays.asList("(51)4356-5678","(51)98789-5677"));
		cli2.getTelefones().addAll(Arrays.asList("(91)-3454-9800","(51)9876-9876"));
		
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 203", "Jardins", "38220834", cli1, ue1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, ue4);
		Endereco e3 = new Endereco(null,"Av. Doca de Souza Franco","902","Apto. 206","Reduto","66045-67",cli2,ue3);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
		cli2.getEnderecos().addAll(Arrays.asList(e3));
		
		clienteRepository.save(Arrays.asList(cli1,cli2));
		enderecoRepository.save(Arrays.asList(e1,e2,e3));
			
		
	}
}
