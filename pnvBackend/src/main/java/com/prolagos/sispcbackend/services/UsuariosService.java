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

import com.prolagos.sispcbackend.domain.PNV_Cad_Usuarios;
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
	
	public PNV_Cad_Usuarios find(Integer id) {
		Optional<PNV_Cad_Usuarios> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + PNV_Cad_Usuarios.class.getName()));
	}
	
	public PNV_Cad_Usuarios findByEmail(String email) {
        final PNV_Cad_Usuarios obj = this.repo.findByEmail(email);
        return obj;
    }

	
	@Transactional
	public PNV_Cad_Usuarios insert(PNV_Cad_Usuarios obj) {
		obj.setUsuarioId(null);
		obj.setSenha(pe.encode("123"));
		obj = repo.save(obj);
		return obj;
	}
	
	
	public PNV_Cad_Usuarios update(PNV_Cad_Usuarios obj) {
		PNV_Cad_Usuarios oldObj = find(obj.getUsuarioId());
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
	
	public List<PNV_Cad_Usuarios> findAll() {
		return repo.findAll();
	}
	
	public Page<PNV_Cad_Usuarios> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public PNV_Cad_Usuarios fromDTO(UsuarioDTO objDto) {
		return new PNV_Cad_Usuarios(objDto.getUsuarioId(), objDto.getNome(), objDto.getEmail(), objDto.getLogin(),null, 
				objDto.getAtivo(), null, null);
	}
	
	public PNV_Cad_Usuarios fromDTO(UsuarioNewDTO objDto) {
		PNV_Cad_Usuarios usu = new PNV_Cad_Usuarios(null, objDto.getNome(), objDto.getEmail(), objDto.getLogin(),
				pe.encode("123"), objDto.getAtivo(), null, null);
		
		return usu;
	}
	
	public PNV_Cad_Usuarios updateSenha(PNV_Cad_Usuarios obj) {
		PNV_Cad_Usuarios newObj = find(obj.getUsuarioId());
		updateDatas(newObj, obj);
		return repo.save(newObj);
	}
	
	public PNV_Cad_Usuarios updateFromDTO(UsuarioDTO objDto) {
		return new PNV_Cad_Usuarios( objDto.getUsuarioId(), objDto.getNome(),objDto.getEmail(), objDto.getLogin(),
		pe.encode(objDto.getSenha()), objDto.getAtivo(),null, null);
	}

	private void updateDatas(PNV_Cad_Usuarios newObj, PNV_Cad_Usuarios obj) {
		newObj.setSenha(obj.getSenha());
	}
	
	private void updateData(PNV_Cad_Usuarios newObj, PNV_Cad_Usuarios obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
		newObj.setTurmas(obj.getTurmas());
	}
}
