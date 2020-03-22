package com.prolagos.sispcbackend.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor 
@EqualsAndHashCode(of = "Id")
public class PNV_Cad_BancoDeQuestoes implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter @Setter private Integer Id;

	@Column(name = "DataDoCadastro", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	@Getter @Setter private String DataDoCadastro;
	
	@Getter @Setter private String Questao;
	@Getter @Setter private String Opc1;
	@Getter @Setter private String Opc2;
	@Getter @Setter private String Opc3;
	@Getter @Setter private String Opc4;
	@Getter @Setter private String Opc5;
	@Getter @Setter private Integer Resp;
	

	@JsonIgnore
	@OneToMany(mappedBy="BancoDeQuestoes")
	@Setter private List<PNV_Modulos_Atividades_Questoes> AtividadesQuestoes;
}
