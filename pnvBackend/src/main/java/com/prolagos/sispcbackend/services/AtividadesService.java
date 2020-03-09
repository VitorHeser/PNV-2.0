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

import com.prolagos.sispcbackend.domain.Cad_PNV_Atividades;
import com.prolagos.sispcbackend.repositories.AtividadesRepository;
import com.prolagos.sispcbackend.services.exceptions.DataIntegrityException;

@Service
public class AtividadesService {
	
	@Autowired
	private AtividadesRepository repo;

	public List<Cad_PNV_Atividades> findAll() {
		return repo.findAll();
	}

	public Page<Cad_PNV_Atividades> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Cad_PNV_Atividades find(Integer id) {
		Optional<Cad_PNV_Atividades> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + 
		Cad_PNV_Atividades.class.getName(), null));
	}

	public List<Cad_PNV_Atividades> findByModulo(Integer id) {
		return  repo.findByModulo(id);
	}
	
	public Cad_PNV_Atividades insert(Cad_PNV_Atividades obj) {
		obj.setAtividadeId(null);  //Utilizado em Entidade Com auto incremento
		obj = repo.save(obj);
		return obj;
	}
	
	public Cad_PNV_Atividades update(Cad_PNV_Atividades obj) {
		find(obj.getAtividadeId());
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
