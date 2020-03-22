package com.prolagos.sispcbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prolagos.sispcbackend.domain.PNV_Cad_Turmas;

@Repository
public interface TurmasRepository extends JpaRepository<PNV_Cad_Turmas, Integer> {
	
}
