package com.prolagos.sispcbackend.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor 
@EqualsAndHashCode(of = "atividadeId")
public class Cad_PNV_Atividades implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter @Setter private Integer atividadeId;
	@Getter @Setter private String nomeDaAtividade;
	@Column(name = "Prazo", nullable = false, updatable = true, insertable = true, columnDefinition = "TIMESTAMP")
	@Getter @Setter private String Prazo;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="fk_moduloId",foreignKey = @ForeignKey(name="fk_modulos_atividades"))
	@Getter @Setter private Cad_PNV_Modulos modulo;

	@JsonIgnore
	@OneToMany(mappedBy="atividade")
	@Getter @Setter private List<Cad_PNV_AtividadesRealizadas> atividadeRealizada;
}
