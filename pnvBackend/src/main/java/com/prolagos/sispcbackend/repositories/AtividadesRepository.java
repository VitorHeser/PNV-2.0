package com.prolagos.sispcbackend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.prolagos.sispcbackend.domain.Cad_PNV_Atividades;

@Repository
public interface AtividadesRepository extends JpaRepository<Cad_PNV_Atividades, Integer>{
	
	@Transactional(readOnly = true)
    @Query("SELECT obj FROM Cad_PNV_Atividades obj WHERE obj.modulos = :modulo")
	List<Cad_PNV_Atividades> findByModulo(@Param("modulo") final Integer modulo);
}
