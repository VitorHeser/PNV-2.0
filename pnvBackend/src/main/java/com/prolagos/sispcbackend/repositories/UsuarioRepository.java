package com.prolagos.sispcbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.prolagos.sispcbackend.domain.Cad_PNV_Usuarios;

@Repository
public interface UsuarioRepository extends JpaRepository<Cad_PNV_Usuarios, Integer> {
	
	@Transactional(readOnly=true)
	Cad_PNV_Usuarios findByEmail(String email);

}
