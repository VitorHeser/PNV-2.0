package com.prolagos.sispcbackend.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor 
@EqualsAndHashCode(of = "moduloId")
public class Cad_PNV_Modulos implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter @Setter private Integer moduloId;
	@Getter @Setter private String modulo;
	@Getter @Setter private String nivel;
	

	@JsonIgnore
	@Fetch(FetchMode.SUBSELECT) 
	@ManyToMany(mappedBy="modulos", fetch = FetchType.EAGER)
	@Getter @Setter private List<Cad_PNV_Turmas> turmas;
	


	@JsonIgnore
	@OneToMany(mappedBy="modulos")
	@Getter @Setter private List<Cad_PNV_Atividades> atividades= new ArrayList<>();
	
	public Cad_PNV_Modulos(Integer moduloId, String modulo, String nivel) {
		super();
		this.moduloId = moduloId;
		this.modulo = modulo;
		this.nivel = nivel;
	}
	
}
