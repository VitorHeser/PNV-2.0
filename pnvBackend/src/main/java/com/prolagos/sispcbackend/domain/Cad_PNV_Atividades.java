package com.prolagos.sispcbackend.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor 
@Table(name="Cad_PNV_Atividades")
@EqualsAndHashCode(of = "atividadeId")
public class Cad_PNV_Atividades implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Getter @Setter private Integer atividadeId;
	@Getter @Setter private String nomeDaAtividade;
	@Column(name = "Prazo", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	@Getter @Setter private String Prazo;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="fk_moduloId",foreignKey = @ForeignKey(name="fk_modulos_atividades"))
	@Getter @Setter private Cad_PNV_Modulos modulos;

}
