package com.prolagos.sispcbackend.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;

import com.prolagos.sispcbackend.domain.Cad_PNV_Usuarios;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class UsuarioDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Getter @Setter private Integer usuarioId;
	
	//@NotEmpty(message="Preenchimento obrigatório")
	//@Length(min=5, max=120, message="O tamanho deve ser entre 5 e 120 caracteres")
	@Getter @Setter private String nome;
	//@NotEmpty(message="Preenchimento obrigatório")
	@Email(message="Email inválido")
	@Getter @Setter private String email;
	@Getter @Setter private String login;
	@Getter @Setter private Boolean ativo;
	@Getter @Setter private String senha;
	@Getter @Setter private String tipo;
	@Getter @Setter private String foto;
	
	

	public UsuarioDTO(Cad_PNV_Usuarios obj) {
		
		usuarioId = obj.getUsuarioId();
		nome = obj.getNome();
		email = obj.getEmail();
		login = obj.getLogin();
		ativo = obj.getAtivo();
		senha = obj.getPassword();
		tipo = obj.getTipo();
		foto = obj.getFoto();
		
	}
	
}
