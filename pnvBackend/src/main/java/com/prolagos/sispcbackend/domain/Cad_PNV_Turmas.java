package com.prolagos.sispcbackend.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@EqualsAndHashCode(of = "turmaId")
public class Cad_PNV_Turmas implements GrantedAuthority {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Getter @Setter private Integer turmaId;
	@Getter @Setter private String Descricao;
	@Getter @Setter private Integer Ano;
	
	@JsonIgnore
	@Fetch(FetchMode.SUBSELECT) 
	@ManyToMany(mappedBy="turmas", fetch = FetchType.EAGER)
	@Getter @Setter private List<Cad_PNV_Usuarios> usuarios;


	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "cad_relc_TurmasModulos",
		joinColumns = @JoinColumn(name = "fk_turmaId", foreignKey = @ForeignKey(name="fk_turma_modulo")),
		inverseJoinColumns = @JoinColumn(name = "fk_moduloId", foreignKey = @ForeignKey(name="fk_modulo_turma")))
	@Fetch(FetchMode.SUBSELECT) 
	@Getter @Setter private List<Cad_PNV_Modulos> modulos;

	public String getAuthority() {
		return this.Descricao;
	}



	public Cad_PNV_Turmas(Integer turmaId, String descricao, Integer ano, List<Cad_PNV_Usuarios> usuarios) {
		super();
		this.turmaId = turmaId;
		Descricao = descricao;
		Ano = ano;
		this.usuarios = usuarios;
	}
		
}
