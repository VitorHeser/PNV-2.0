package com.prolagos.sispcbackend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prolagos.sispcbackend.domain.Cad_PNV_Usuarios;
import com.prolagos.sispcbackend.dto.UsuarioDTO;
import com.prolagos.sispcbackend.dto.UsuarioNewDTO;
import com.prolagos.sispcbackend.repositories.UsuarioRepository;
import com.prolagos.sispcbackend.services.exceptions.DataIntegrityException;
import com.prolagos.sispcbackend.services.exceptions.ObjectNotFoundException;

@Service
public class UsuariosService {

	@Autowired
	private UsuarioRepository repo;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	public Cad_PNV_Usuarios find(Integer id) {
		Optional<Cad_PNV_Usuarios> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cad_PNV_Usuarios.class.getName()));
	}
	
	public Cad_PNV_Usuarios findByEmail(String email) {
        final Cad_PNV_Usuarios obj = this.repo.findByEmail(email);
        return obj;
    }

	
	@Transactional
	public Cad_PNV_Usuarios insert(Cad_PNV_Usuarios obj) {
		obj.setUsuarioId(null);
		obj.setSenha(pe.encode("123"));
		obj = repo.save(obj);
		return obj;
	}
	
	
	public Cad_PNV_Usuarios update(Cad_PNV_Usuarios obj) {
		Cad_PNV_Usuarios oldObj = find(obj.getUsuarioId());
		updateData(oldObj, obj);
		return repo.save(oldObj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir porque há pedidos relacionados");
		}
	}
	
	public List<Cad_PNV_Usuarios> findAll() {
		return repo.findAll();
	}
	
	public Page<Cad_PNV_Usuarios> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Cad_PNV_Usuarios fromDTO(UsuarioDTO objDto) {
		return new Cad_PNV_Usuarios(objDto.getUsuarioId(), objDto.getNome(), objDto.getEmail(), objDto.getLogin(),null, 
				objDto.getAtivo(), null, null);
	}
	
	public Cad_PNV_Usuarios fromDTO(UsuarioNewDTO objDto) {
		Cad_PNV_Usuarios usu = new Cad_PNV_Usuarios(null, objDto.getNome(), objDto.getEmail(), objDto.getLogin(),
				pe.encode("123"), objDto.getAtivo(), null, null);
		
		return usu;
	}
	
	public Cad_PNV_Usuarios updateSenha(Cad_PNV_Usuarios obj) {
		Cad_PNV_Usuarios newObj = find(obj.getUsuarioId());
		updateDatas(newObj, obj);
		return repo.save(newObj);
	}
	
	public Cad_PNV_Usuarios updateFromDTO(UsuarioDTO objDto) {
		return new Cad_PNV_Usuarios( objDto.getUsuarioId(), objDto.getNome(),objDto.getEmail(), objDto.getLogin(),
		pe.encode(objDto.getSenha()), objDto.getAtivo(),null, null);
	}

	private void updateDatas(Cad_PNV_Usuarios newObj, Cad_PNV_Usuarios obj) {
		newObj.setSenha(obj.getSenha());
	}
	
	private void updateData(Cad_PNV_Usuarios newObj, Cad_PNV_Usuarios obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
		newObj.setPerfis(obj.getPerfis());
	}
}
