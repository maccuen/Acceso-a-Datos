package com.gregoriofer.accesodatos.ejemplo.service;

import com.gregoriofer.accesodatos.ejemplo.dto.*;
import com.gregoriofer.accesodatos.ejemplo.model.Alumno;
import com.gregoriofer.accesodatos.ejemplo.model.Ciclo;
import com.gregoriofer.accesodatos.ejemplo.repository.AlumnoJPARepository;
import com.gregoriofer.accesodatos.ejemplo.repository.AlumnoRepository;
import com.gregoriofer.accesodatos.ejemplo.repository.CicloJPARepository;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service("servicioAlumnos")
public class AlumnoServiceImpl implements AlumnoService {

    private final AlumnoJPARepository alumnoJPARepository;
    private final CicloJPARepository cicloJPARepository;
    private final AlumnoRepository alumnoRepository;

    public AlumnoServiceImpl(final AlumnoJPARepository alumnoRepository,
                             final CicloJPARepository cicloJPARepository,
                             final AlumnoRepository buscadorDeAlumnosRepository) {
        this.alumnoJPARepository = alumnoRepository;
        this.cicloJPARepository = cicloJPARepository;
        this.alumnoRepository = buscadorDeAlumnosRepository;
    }

    @Override
    public AlumnoDto grabar(final PeticionDeCreacionDeAlumno data) {
        final List<Ciclo> ciclos = cicloJPARepository.findCicloByCodigo(data.getCiclo());

        final Alumno entidad = convert(data, ciclos.get(0));

        String codigo = generarCodigoDeAlumno();
        while (!CollectionUtils.isEmpty(alumnoJPARepository.findAlumnoByCodigo(codigo))) {
            codigo = generarCodigoDeAlumno();
        }
        entidad.setCodigo(codigo);

        final Alumno entidadGrabada = alumnoJPARepository.save(entidad);
        return convert(entidadGrabada);
    }

    @Override
    public AlumnoDto actualizar(final String codigo, final PeticionDeCreacionDeAlumno data) {
        final List<Alumno> alumnos = alumnoJPARepository.findAlumnoByCodigo(codigo);
        if (!CollectionUtils.isEmpty(alumnos)) {
            final Alumno entidadAActualizar = alumnos.get(0);
            final List<Ciclo> ciclos = cicloJPARepository.findCicloByCodigo(data.getCiclo());
            copyData(entidadAActualizar, data, ciclos.get(0));

            final Alumno entidadGrabada = alumnoJPARepository.save(entidadAActualizar);
            return convert(entidadGrabada);
        }

        throw new NotFoundException("No existe ningún alumno para ese código");
    }

    @Override
    public AlumnoDto actualizar(final PeticionDeActualizacionDeAlumno data) {
        final List<Alumno> alumnos = alumnoJPARepository.findAlumnoByCodigo(data.getCodigo());
        if (!CollectionUtils.isEmpty(alumnos)) {
            final Alumno entidadAActualizar = alumnos.get(0);
            final List<Ciclo> ciclos = cicloJPARepository.findCicloByCodigo(data.getCiclo());
            copyData(entidadAActualizar, data, ciclos.get(0));

            final Alumno entidadGrabada = alumnoJPARepository.save(entidadAActualizar);
            return convert(entidadGrabada);
        }

        throw new NotFoundException("No existe ningún alumno para ese código");
    }

    @Override
    public List<AlumnoDto> listar() {
        final List<Alumno> alumnos = alumnoJPARepository.findAll();
        return convert(alumnos);
    }

    @Override
    public void eliminar(final String codigo) {
        final List<Alumno> alumnos = alumnoJPARepository.findAlumnoByCodigo(codigo);
        if (!CollectionUtils.isEmpty(alumnos)) {
            alumnoJPARepository.delete(alumnos.get(0));
        }
        throw new NotFoundException("No existe ningún alumno para ese código");
    }

    @Override
    public List<AlumnoDto> buscar(final PeticionDeBusquedaDeAlumno filtro) {
        final List<Alumno> alumnos = alumnoRepository.buscar(filtro);
        return convert(alumnos);
    }

    @Override
    public List<AlumnoDto> buscar(final String codigo, final String nombre) {
        final List<Alumno> alumnos = StringUtils.isEmpty(nombre) ? alumnoJPARepository.findAlumnoByCodigo(codigo) :
                alumnoJPARepository.findAlumnoByCodigoAndNombre(codigo, nombre);
        return convert(alumnos);
    }

    private List<AlumnoDto> convert(final List<Alumno> alumnos) {
        return alumnos.stream().map(AlumnoServiceImpl::convert).collect(Collectors.toList());
    }

    private static Alumno convert(final PeticionDeCreacionDeAlumno dto, final Ciclo ciclo) {
        final Alumno entidad = new Alumno();
        entidad.setNombre(dto.getNombre());
        entidad.setApellidos(dto.getApellidos());
        entidad.setDni(dto.getDni());
        entidad.setCiclo(ciclo);
        entidad.setCurso(dto.getCurso());

        return entidad;
    }

    private static void copyData(final Alumno alumno, final PeticionDeCreacionDeAlumno dto,
                                 final Ciclo ciclo) {
        alumno.setNombre(dto.getNombre());
        alumno.setApellidos(dto.getApellidos());
        alumno.setDni(dto.getDni());
        alumno.setCiclo(ciclo);
        alumno.setCurso(dto.getCurso());
    }

    private static void copyData(final Alumno alumno, final PeticionDeActualizacionDeAlumno dto,
                                 final Ciclo ciclo) {
        alumno.setNombre(dto.getNombre());
        alumno.setApellidos(dto.getApellidos());
        alumno.setDni(dto.getDni());
        alumno.setCiclo(ciclo);
        alumno.setCurso(dto.getCurso());
    }

    private static AlumnoDto convert(final Alumno alumno) {
        final AlumnoDto dto = new AlumnoDto();
        dto.setNombre(alumno.getNombre());
        dto.setApellidos(alumno.getApellidos());
        dto.setCodigo(alumno.getCodigo());
        dto.setDni(alumno.getDni());
        dto.setCiclo(convert(alumno.getCiclo()));
        dto.setCurso(alumno.getCurso());

        return dto;
    }

    private static CicloDto convert(final Ciclo ciclo) {
        final CicloDto dto = new CicloDto();
        dto.setNombre(ciclo.getNombre());
        dto.setCodigo(ciclo.getCodigo());

        return dto;
    }

    private static String generarCodigoDeAlumno() {
        return generarCodigoDeAlumno(0, 10000).toString();
    }

    private static Integer generarCodigoDeAlumno(final Integer min, final Integer max) {
        return new Random().nextInt(max - min) + min;
    }
}
