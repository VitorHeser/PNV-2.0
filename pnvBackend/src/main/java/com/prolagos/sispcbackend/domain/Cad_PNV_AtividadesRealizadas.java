package com.prolagos.sispcbackend.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor 
@EqualsAndHashCode(of = "idatividade")
public class Cad_PNV_AtividadesRealizadas implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Getter @Setter private Integer idatividade;
	@Column(name = "DataRealizado", nullable = false, updatable = true, insertable = true, columnDefinition = "CURRENT_TIMESTAMP")
	@Getter @Setter private String DataRealizado;
	@Getter @Setter private Double Nota;
	@Getter @Setter private Integer Usuario;
	@Getter @Setter private Integer AtividadeRelacionada;
	

	
}
