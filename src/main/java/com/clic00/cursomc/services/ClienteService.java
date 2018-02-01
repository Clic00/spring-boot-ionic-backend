package com.clic00.cursomc.services;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.clic00.cursomc.domain.Cidade;
import com.clic00.cursomc.domain.Cliente;
import com.clic00.cursomc.domain.Endereco;
import com.clic00.cursomc.domain.enums.Perfil;
import com.clic00.cursomc.domain.enums.TipoCliente;
import com.clic00.cursomc.dto.ClienteDTO;
import com.clic00.cursomc.dto.ClienteNewDTO;
import com.clic00.cursomc.repositories.CidadeRepository;
import com.clic00.cursomc.repositories.ClienteRepository;
import com.clic00.cursomc.repositories.EnderecoRepository;
import com.clic00.cursomc.security.UserSS;
import com.clic00.cursomc.services.exceptions.AuthorizationException;
import com.clic00.cursomc.services.exceptions.DataIntegrityException;
import com.clic00.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private BCryptPasswordEncoder bCrypt;
	@Autowired
	private ClienteRepository repo;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private S3Service s3service;

	public Cliente find(Integer id) {
		
		UserSS user = UserService.authenticated();
		if((user==null || !user.hasRole(Perfil.ADMIN)) && !id.equals(user.getId())) {
			
			throw new AuthorizationException("Acesso negado!");
		}
		
		
		Cliente obj = repo.findOne(id);
		if (obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id:" + id + ", tipo: " + Cliente.class.getName());
		}

		return obj;
	}

	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj = repo.save(obj);
		enderecoRepository.save(obj.getEnderecos());
		return obj;
	}

	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId());
		updateData(newObj, obj);
		// newObj.setNome(obj.getNome());
		// newObj.setEmail(obj.getEmail());
		return repo.save(newObj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.delete(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir Cliente que possua pedidos! Id:" + id + ", tipo: "
					+ Cliente.class.getName());
		}
	}

	public List<Cliente> findAll() {
		try {
			return repo.findAll();
		} catch (ObjectNotFoundException e) {
			throw new ObjectNotFoundException("Objeto não encontrado! Tipo: " + Cliente.class.getName());
		}
	}

	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	public Cliente fromDTO(ClienteDTO objDTO) {
		return new Cliente(objDTO.getId(), objDTO.getNome(), objDTO.getEmail(), null, null, null);
	}

	public Cliente fromDTO(ClienteNewDTO objDTO) {

		Cliente cliente = new Cliente(null, objDTO.getNome(), objDTO.getEmail(), objDTO.getCpfOuCnpj(),
				TipoCliente.toEnum(objDTO.getTipo()), bCrypt.encode(objDTO.getSenha()));

		Cidade cidade = cidadeRepository.findOne(objDTO.getCidadeId());

		Endereco endereco = new Endereco(null, objDTO.getLogradouro(), objDTO.getNumero(), objDTO.getComplemento(),
				objDTO.getBairro(), objDTO.getCep(), cliente, cidade);
		cliente.getEnderecos().add(endereco);
		cliente.getTelefones().add(objDTO.getTelefone1());
		if (objDTO.getTelefone2() != null)
			cliente.getTelefones().add(objDTO.getTelefone2());
		if (objDTO.getTelefone3() != null)
			cliente.getTelefones().add(objDTO.getTelefone3());
		return cliente;
	}

	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());

	}
	
	public URI uploadProfilePicture(MultipartFile multipartFile) {
		
		UserSS user = UserService.authenticated();
		
		if(user == null) {
			throw new AuthorizationException("Acesso negado!");
		}
		
		URI uri = s3service.uploadFile(multipartFile);
		
		Cliente cliente = repo.findOne(user.getId());
		
		cliente.setUrlImage(uri.toString());
		repo.save(cliente);
		return uri;
		
	}
}
