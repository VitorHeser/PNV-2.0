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
@EqualsAndHashCode(of = "Id")
public class PNV_Modulos implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter @Setter private Integer Id;
	@Getter @Setter private String NomeDoModulo;
	@Getter @Setter private String Descricao;
	@Getter @Setter private Double NotaFinal;

	@Column(name = "DataDoCadastro", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	@Getter @Setter private String DataDoCadastro;

	@Column(name = "Prazo", nullable = false, updatable = true, insertable = true, columnDefinition = "DATE")
	@Getter @Setter private String Prazo;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="turma", foreignKey = @ForeignKey(name="fk_turmasModulos"))
	@Setter private PNV_Cad_Turmas turmas;

	@JsonIgnore
	@OneToMany(mappedBy="Modulos")
	@Getter @Setter private List<PNV_Modulos_Atividades> Atividades;
	
	
}
