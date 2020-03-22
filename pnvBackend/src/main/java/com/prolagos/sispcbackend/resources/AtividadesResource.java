package com.prolagos.sispcbackend.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.prolagos.sispcbackend.domain.PNV_Modulos_Atividades;
import com.prolagos.sispcbackend.services.AtividadesService;

@RestController
@RequestMapping(value="/atividades")
public class AtividadesResource {
	
	@Autowired
	private AtividadesService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<PNV_Modulos_Atividades> find(@PathVariable Integer id) {
		PNV_Modulos_Atividades obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}

//	@RequestMapping(value="/modulo/{id}", method=RequestMethod.GET)
//	public ResponseEntity<List<PNV_Modulos_Atividades>> findByModulo(@PathVariable Integer id) {
//		List<PNV_Modulos_Atividades> obj = service.findByModulo(id);
//		return ResponseEntity.ok().body(obj);
//	}
	
	@RequestMapping(method=RequestMethod.POST)
		public ResponseEntity<Void> insert(@RequestBody PNV_Modulos_Atividades obj) {
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody PNV_Modulos_Atividades obj, @PathVariable Integer id) {
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<PNV_Modulos_Atividades>> findAll() {
		List<PNV_Modulos_Atividades> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@RequestMapping(value="/page", method=RequestMethod.GET)
	public ResponseEntity<Page<PNV_Modulos_Atividades>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="moduloId") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		Page<PNV_Modulos_Atividades> list = service.findPage(page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok().body(list);
	}

}
