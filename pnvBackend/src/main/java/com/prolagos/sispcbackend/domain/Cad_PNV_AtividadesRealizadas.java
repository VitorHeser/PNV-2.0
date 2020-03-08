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
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor 
@EqualsAndHashCode(of = "atividaderealId")
public class Cad_PNV_AtividadesRealizadas implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter @Setter private Integer atividaderealId;
	@Column(name = "DataRealizado", nullable = false, updatable = true, insertable = true, columnDefinition = "CURRENT_TIMESTAMP")
	@Getter @Setter private String DataRealizado;
	


	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="fk_ativrealId",foreignKey = @ForeignKey(name="fk_ativReal_atividades"))
	@Getter @Setter private Cad_PNV_Atividades atividade;
	
	
}
