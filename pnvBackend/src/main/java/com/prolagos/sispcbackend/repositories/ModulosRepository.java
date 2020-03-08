package com.prolagos.sispcbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prolagos.sispcbackend.domain.Cad_PNV_Modulos;

@Repository
public interface ModulosRepository extends JpaRepository<Cad_PNV_Modulos, Integer>{

}
