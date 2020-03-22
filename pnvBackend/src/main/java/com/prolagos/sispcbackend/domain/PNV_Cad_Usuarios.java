package com.prolagos.sispcbackend.domain;

import java.util.ArrayList;
import java.util.Collection;
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
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor 
@EqualsAndHashCode(of = "usuarioId")
@Entity
public class PNV_Cad_Usuarios implements UserDetails {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Getter @Setter private Integer usuarioId;
	@Getter @Setter private String nome;
	@Getter @Setter private String email;
	@Getter @Setter private String tipo;
	@Getter @Setter private String login;
	@JsonIgnore
	@Getter @Setter private String senha;
	@Getter @Setter private Boolean ativo;
	@Getter @Setter private String foto;
	
	@JsonIgnore
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "pnv_relc_usuariosTurmas",
		joinColumns = @JoinColumn(name = "fk_usuarioId", foreignKey = @ForeignKey(name="fk_usuario_turma")),
		inverseJoinColumns = @JoinColumn(name = "fk_turmaId", foreignKey = @ForeignKey(name="fk_turma_usuarios")))
	@Fetch(FetchMode.SUBSELECT) 
	@Getter @Setter private List<PNV_Cad_Turmas> turmas;
	
	@JsonIgnore
	@OneToMany(mappedBy="usuarioId")
	@Getter @Setter private List<PNV_Cad_Auth_Usuarios> usuarios= new ArrayList<>();

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.turmas;
	}

	
	@Override
	public String getPassword() {
		return this.senha;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	
	public PNV_Cad_Usuarios(Integer usuarioId, String nome, String email, String login, String senha, Boolean ativo,
			String tipo, String foto) {
		super();
		this.usuarioId = usuarioId;
		this.nome = nome;
		this.email = email;
		this.login = login;
		this.senha = senha;
		this.ativo = ativo;
		this.tipo = tipo;
		this.foto = foto;
	}
	
	

}
