package com.gregoriofer.accesodatos.ejemplo.repository;

import com.gregoriofer.accesodatos.ejemplo.model.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlumnoJPARepository extends JpaRepository<Alumno, Long> {

    List<Alumno> findAlumnoByCodigo(final String codigo);

    List<Alumno> findAlumnoByCodigoAndNombre(final String codigo, final String nombre);
}
