package com.prolagos.sispcbackend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prolagos.sispcbackend.domain.Cad_PNV_Turmas;
import com.prolagos.sispcbackend.repositories.TurmasRepository;
import com.prolagos.sispcbackend.services.exceptions.DataIntegrityException;
import com.prolagos.sispcbackend.services.exceptions.ObjectNotFoundException;

@Service
public class TurmasService {

	@Autowired
	private TurmasRepository repo;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	public Cad_PNV_Turmas find(Integer id) {
		Optional<Cad_PNV_Turmas> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cad_PNV_Turmas.class.getName()));
	}
	

	@Transactional
	public Cad_PNV_Turmas insert(Cad_PNV_Turmas obj) {
		obj.setTurmaId(null);
		obj = repo.save(obj);
		return obj;
	}
	
	
	public Cad_PNV_Turmas update(Cad_PNV_Turmas obj) {
		Cad_PNV_Turmas oldObj = find(obj.getTurmaId());
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
	
	public List<Cad_PNV_Turmas> findAll() {
		return repo.findAll();
	}
	



}
