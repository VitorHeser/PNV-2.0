package com.prolagos.sispcbackend.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
@EqualsAndHashCode(of = "Id")
public class PNV_Modulos_Atividades_Questoes implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Getter @Setter private Integer Id;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="BancoDeQuestoes", foreignKey = @ForeignKey(name="fk_Atividade_BancoDeQuestoes"))
	@Setter private PNV_Cad_BancoDeQuestoes BancoDeQuestoes;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="AtividadesQuestoes", foreignKey = @ForeignKey(name="fk_Atividades_AtividadesQuestoes"))
	@Setter private PNV_Modulos_Atividades Atividades;
	
	
}
