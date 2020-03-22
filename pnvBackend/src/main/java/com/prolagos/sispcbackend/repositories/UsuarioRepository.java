package com.prolagos.sispcbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.prolagos.sispcbackend.domain.PNV_Cad_Usuarios;

@Repository
public interface UsuarioRepository extends JpaRepository<PNV_Cad_Usuarios, Integer> {
	
	@Transactional(readOnly=true)
	PNV_Cad_Usuarios findByEmail(String email);

}
