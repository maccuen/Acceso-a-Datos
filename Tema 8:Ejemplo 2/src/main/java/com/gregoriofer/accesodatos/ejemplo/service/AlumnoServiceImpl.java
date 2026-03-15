package com.gregoriofer.accesodatos.ejemplo.service;

import com.gregoriofer.accesodatos.ejemplo.dto.AlumnoDto;
import com.gregoriofer.accesodatos.ejemplo.dto.PeticionDeActualizacionDeAlumno;
import com.gregoriofer.accesodatos.ejemplo.dto.PeticionDeCreacionDeAlumno;
import com.gregoriofer.accesodatos.ejemplo.model.Alumno;
import com.gregoriofer.accesodatos.ejemplo.repository.AlumnoRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service("servicioAlumnos")
public class AlumnoServiceImpl implements AlumnoService {

    private final AlumnoRepository alumnoRepository;

    public AlumnoServiceImpl(final AlumnoRepository alumnoRepository) {
        this.alumnoRepository = alumnoRepository;
    }

    @Override
    public AlumnoDto grabar(final PeticionDeCreacionDeAlumno data) {
        final Alumno entidad = convert(data);

        String codigo = generarCodigoDeAlumno();
        while (!CollectionUtils.isEmpty(alumnoRepository.findAlumnoByCodigo(codigo))) {
            codigo = generarCodigoDeAlumno();
        }
        entidad.setCodigo(codigo);

        final Alumno entidadGrabada = alumnoRepository.save(entidad);
        return convert(entidadGrabada);
    }

    @Override
    public AlumnoDto actualizar(final String codigo, final PeticionDeCreacionDeAlumno data) {
        final List<Alumno> alumnos = alumnoRepository.findAlumnoByCodigo(codigo);
        if (!CollectionUtils.isEmpty(alumnos)) {
            final Alumno entidadAActualizar = alumnos.get(0);
            copyData(entidadAActualizar, data);

            final Alumno entidadGrabada = alumnoRepository.save(entidadAActualizar);
            return convert(entidadGrabada);
        }

        throw new NotFoundException("No existe ningún alumno para ese código");
    }

    @Override
    public AlumnoDto actualizar(final PeticionDeActualizacionDeAlumno data) {
        final List<Alumno> alumnos = alumnoRepository.findAlumnoByCodigo(data.getCodigo());
        if (!CollectionUtils.isEmpty(alumnos)) {
            final Alumno entidadAActualizar = alumnos.get(0);
            copyData(entidadAActualizar, data);

            final Alumno entidadGrabada = alumnoRepository.save(entidadAActualizar);
            return convert(entidadGrabada);
        }

        throw new NotFoundException("No existe ningún alumno para ese código");
    }

    @Override
    public List<AlumnoDto> listar() {
        final List<Alumno> alumnos = alumnoRepository.findAll();
        return convert(alumnos);
    }

    @Override
    public void eliminar(final String codigo) {
        final List<Alumno> alumnos = alumnoRepository.findAlumnoByCodigo(codigo);
        if (!CollectionUtils.isEmpty(alumnos)) {
            alumnoRepository.delete(alumnos.get(0));
        }
        throw new NotFoundException("No existe ningún alumno para ese código");
    }

    @Override
    public List<AlumnoDto> buscar(final String codigo, final String nombre) {
        final List<Alumno> alumnos = StringUtils.isEmpty(nombre) ? alumnoRepository.findAlumnoByCodigo(codigo) :
                alumnoRepository.findAlumnoByCodigoAndNombre(codigo, nombre);
        return convert(alumnos);
    }

    private static List<AlumnoDto> convert(final List<Alumno> alumnos) {
        return alumnos.stream().map(AlumnoServiceImpl::convert).collect(Collectors.toList());
    }

    private static Alumno convert(final PeticionDeCreacionDeAlumno dto) {
        final Alumno entidad = new Alumno();
        entidad.setNombre(dto.getNombre());
        entidad.setApellidos(dto.getApellidos());
        entidad.setDni(dto.getDni());
        entidad.setCiclo(dto.getCiclo());
        entidad.setCurso(dto.getCurso());

        return entidad;
    }

    private static void copyData(final Alumno alumno, final PeticionDeCreacionDeAlumno dto) {
        alumno.setNombre(dto.getNombre());
        alumno.setApellidos(dto.getApellidos());
        alumno.setDni(dto.getDni());
        alumno.setCiclo(dto.getCiclo());
        alumno.setCurso(dto.getCurso());
    }

    private static void copyData(final Alumno alumno, final PeticionDeActualizacionDeAlumno dto) {
        alumno.setNombre(dto.getNombre());
        alumno.setApellidos(dto.getApellidos());
        alumno.setDni(dto.getDni());
        alumno.setCiclo(dto.getCiclo());
        alumno.setCurso(dto.getCurso());
    }

    private static AlumnoDto convert(final Alumno alumno) {
        final AlumnoDto dto = new AlumnoDto();
        dto.setNombre(alumno.getNombre());
        dto.setApellidos(alumno.getApellidos());
        dto.setCodigo(alumno.getCodigo());
        dto.setDni(alumno.getDni());
        dto.setCiclo(alumno.getCiclo());
        dto.setCurso(alumno.getCurso());

        return dto;
    }

    private static String generarCodigoDeAlumno() {
        return generarCodigoDeAlumno(0, 10000).toString();
    }

    private static Integer generarCodigoDeAlumno(final Integer min, final Integer max) {
        return new Random().nextInt(max - min) + min;
    }
}
