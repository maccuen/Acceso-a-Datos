package com.gregoriofer.accesodatos.ejemplo.repository;

import com.gregoriofer.accesodatos.ejemplo.model.Ciclo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CicloJPARepository extends JpaRepository<Ciclo, Long> {

    List<Ciclo> findCicloByCodigo(final String codigo);
}
