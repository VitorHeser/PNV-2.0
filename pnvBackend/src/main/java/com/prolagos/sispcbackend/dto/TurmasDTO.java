package com.prolagos.sispcbackend.dto;

import java.io.Serializable;
import java.util.List;

import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.prolagos.sispcbackend.domain.PNV_Modulos;
import com.prolagos.sispcbackend.domain.PNV_Cad_Turmas;
import com.prolagos.sispcbackend.domain.PNV_Cad_Usuarios;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class TurmasDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Getter @Setter private Integer turmaId;
	@Getter @Setter private String Descricao;
	@Getter @Setter private Integer Ano;
	
	@OneToMany(mappedBy="turmas")
	@Getter @Setter private List<PNV_Modulos> modulos;
	
	public TurmasDTO(PNV_Cad_Turmas obj) {
		
		turmaId = obj.getTurmaId();
		Descricao = obj.getDescricao();
		Ano = obj.getAno();
		modulos = obj.getModulos();
		
	}
}
