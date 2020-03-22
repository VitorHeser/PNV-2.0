package com.prolagos.sispcbackend.services;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.prolagos.sispcbackend.domain.PNV_Modulos_Atividades;
import com.prolagos.sispcbackend.repositories.AtividadesRepository;
import com.prolagos.sispcbackend.services.exceptions.DataIntegrityException;

@Service
public class AtividadesService {
	
	@Autowired
	private AtividadesRepository repo;

	public List<PNV_Modulos_Atividades> findAll() {
		return repo.findAll();
	}

	public Page<PNV_Modulos_Atividades> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public PNV_Modulos_Atividades find(Integer id) {
		Optional<PNV_Modulos_Atividades> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + 
		PNV_Modulos_Atividades.class.getName(), null));
	}

//	public List<PNV_Modulos_Atividades> findByModulo(Integer id) {
//		return  repo.findByModulo(id);
//	}
	
	public PNV_Modulos_Atividades insert(PNV_Modulos_Atividades obj) {
		obj.setId(null);  //Utilizado em Entidade Com auto incremento
		obj = repo.save(obj);
		return obj;
	}
	
	public PNV_Modulos_Atividades update(PNV_Modulos_Atividades obj) {
		find(obj.getId());
		return repo.save(obj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir um objeto que possui relacionamentos");
		}
	}

}
