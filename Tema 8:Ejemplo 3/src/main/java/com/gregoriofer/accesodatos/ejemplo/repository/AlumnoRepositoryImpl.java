package com.gregoriofer.accesodatos.ejemplo.repository;

import com.gregoriofer.accesodatos.ejemplo.dto.PeticionDeBusquedaDeAlumno;
import com.gregoriofer.accesodatos.ejemplo.model.Alumno;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@Repository
public class AlumnoRepositoryImpl implements AlumnoRepository {

    @PersistenceContext
    private EntityManager entityManager;

    private static List<Predicate> crearElFiltro(final CriteriaBuilder criteriaBuilder, final Root<Alumno> tablaPrincipal,
                                                 final PeticionDeBusquedaDeAlumno filtro) {
        /**
         * WHERE ..... AND .... AND ....
         */

        final List<Predicate> condiciones = new ArrayList<>();

        if (StringUtils.isNotEmpty(filtro.getCodigo())) {
            condiciones.add(criteriaBuilder.equal(tablaPrincipal.get("codigo"), filtro.getCodigo()));
        }

        if (StringUtils.isNotEmpty(filtro.getNombre())) {
            condiciones.add(criteriaBuilder.like(tablaPrincipal.get("nombre").as(String.class),
                    "%" + filtro.getNombre() + "%"));
        }

        if (StringUtils.isNotEmpty(filtro.getApellidos())) {
            condiciones.add(criteriaBuilder.like(tablaPrincipal.get("apellidos").as(String.class),
                    "%" + filtro.getApellidos() + "%"));
        }

        if (StringUtils.isNotEmpty(filtro.getDni())) {
            condiciones.add(criteriaBuilder.equal(tablaPrincipal.get("dni"), filtro.getDni()));
        }

        if (StringUtils.isNotEmpty(filtro.getCiclo())) {
            condiciones.add(criteriaBuilder.equal(tablaPrincipal.get("ciclo"), filtro.getCiclo()));
        }

        if (filtro.getCurso() != null) {
            condiciones.add(criteriaBuilder.equal(tablaPrincipal.get("curso"), filtro.getCurso()));
        }

        if (filtro.getCursoMinimo() != null) {
            condiciones.add(criteriaBuilder.ge(tablaPrincipal.get("curso"), filtro.getCursoMinimo()));
        }

        if (filtro.getCursoMaximo() != null) {
            condiciones.add(criteriaBuilder.le(tablaPrincipal.get("curso"), filtro.getCursoMaximo()));
        }

        return condiciones;
    }

    @Override
    public List<Alumno> buscar(final PeticionDeBusquedaDeAlumno filtro) {
        try {
            final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            final Long contador = contar(criteriaBuilder, filtro);
            if (contador > 0) {
                return buscar(criteriaBuilder, filtro);
            }
        } catch (Exception ex) {
            log.error("Error to find all: ", ex);
        }

        return Collections.emptyList();
    }

    private Long contar(final CriteriaBuilder criteriaBuilder, final PeticionDeBusquedaDeAlumno filtro) {
        /**
         * SELECT COUNT(*) FROM ALUMNO WHERE ..... AND .... AND ....
         */

        final CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        final Root<Alumno> tablaPrincipal = criteriaQuery.from(Alumno.class);
        criteriaQuery.select(criteriaBuilder.count(tablaPrincipal));

        final List<Predicate> criteriosDeFiltrado = crearElFiltro(criteriaBuilder, tablaPrincipal, filtro);
        final CriteriaQuery<Long> where = criteriaQuery.where(criteriaBuilder.and(
                criteriosDeFiltrado.toArray(new Predicate[criteriosDeFiltrado.size()])));
        final Query consulta = entityManager.createQuery(where);

        final Long contador = (Long) consulta.getSingleResult();
        log.info("Contador {}", contador);
        return contador;
    }

    private List<Alumno> buscar(final CriteriaBuilder criteriaBuilder, final PeticionDeBusquedaDeAlumno filtro) {
        /**
         * SELECT * FROM ALUMNO WHERE ..... AND .... AND .... ORDERBY ... ASC
         */

        final CriteriaQuery<Alumno> criteriaQuery = criteriaBuilder.createQuery(Alumno.class);
        final Root<Alumno> tablaPrincipal = criteriaQuery.from(Alumno.class);
        criteriaQuery.select(tablaPrincipal);

        final List<Predicate> criteriosDeFiltrado = crearElFiltro(criteriaBuilder, tablaPrincipal, filtro);
        final CriteriaQuery<Alumno> where = criteriaQuery.where(criteriaBuilder.and(
                criteriosDeFiltrado.toArray(new Predicate[criteriosDeFiltrado.size()])));

        where.orderBy(criteriaBuilder.asc(tablaPrincipal.get("codigo")));

        final Query consulta = entityManager.createQuery(where);

        final List<Alumno> resultados = consulta.getResultList();

        log.info("Número de resultados: {}", resultados.size());
        return resultados;
    }
}