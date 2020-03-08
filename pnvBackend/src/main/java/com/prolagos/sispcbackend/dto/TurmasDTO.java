package com.prolagos.sispcbackend.dto;

import java.io.Serializable;
import java.util.List;

import javax.persistence.FetchType;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.prolagos.sispcbackend.domain.Cad_PNV_Modulos;
import com.prolagos.sispcbackend.domain.Cad_PNV_Turmas;
import com.prolagos.sispcbackend.domain.Cad_PNV_Usuarios;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class TurmasDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Getter @Setter private Integer turmaId;
	@Getter @Setter private String Descricao;
	@Getter @Setter private Integer Ano;
	
	@Fetch(FetchMode.SUBSELECT) 
	@ManyToMany(mappedBy="turmas", fetch = FetchType.EAGER)
	@Getter @Setter private List<Cad_PNV_Usuarios> usuarios;
	
	
	@Fetch(FetchMode.SUBSELECT) 
	@ManyToMany(mappedBy="turmas", fetch = FetchType.EAGER)
	@Getter @Setter private List<Cad_PNV_Modulos> modulos;
	
	public TurmasDTO(Cad_PNV_Turmas obj) {
		
		turmaId = obj.getTurmaId();
		Descricao = obj.getDescricao();
		Ano = obj.getAno();
		usuarios = obj.getUsuarios();
		modulos = obj.getModulos();
		
	}
}
