package com.prolagos.sispcbackend.domain;

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
import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@EqualsAndHashCode(of = "turmaId")
public class PNV_Cad_Turmas implements GrantedAuthority {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Getter @Setter private Integer turmaId;
	@Getter @Setter private String Descricao;
	@Getter @Setter private Integer Ano;
	
	@JsonIgnore
	@Fetch(FetchMode.SUBSELECT) 
	@ManyToMany(mappedBy="turmas", fetch = FetchType.EAGER)
	@Getter @Setter private List<PNV_Cad_Usuarios> usuarios;

	@JsonIgnore
	@OneToMany(mappedBy="turmas")
	@Getter @Setter private List<PNV_Modulos> modulos;

	
	
	
	
	public String getAuthority() {
		return this.Descricao;
	}
}
