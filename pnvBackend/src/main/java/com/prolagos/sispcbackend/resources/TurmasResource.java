package com.prolagos.sispcbackend.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

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

import com.prolagos.sispcbackend.domain.Cad_PNV_Turmas;
import com.prolagos.sispcbackend.domain.Cad_PNV_Turmas;
import com.prolagos.sispcbackend.dto.TurmasDTO;
import com.prolagos.sispcbackend.dto.UsuarioDTO;
import com.prolagos.sispcbackend.dto.UsuarioNewDTO;
import com.prolagos.sispcbackend.services.TurmasService;

@RestController
@RequestMapping(value="/turmas")
public class TurmasResource {


	@Autowired
	private TurmasService service;

	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<TurmasDTO> find(@PathVariable Integer id) {
		Cad_PNV_Turmas obj = service.find(id);
		TurmasDTO dto = new TurmasDTO(obj);
		return ResponseEntity.ok().body(dto);
	}

	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Cad_PNV_Turmas>> findAll() {
		List<Cad_PNV_Turmas> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	


	
}
