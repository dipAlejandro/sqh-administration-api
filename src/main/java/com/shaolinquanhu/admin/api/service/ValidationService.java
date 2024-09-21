package com.shaolinquanhu.admin.api.service;

import com.shaolinquanhu.admin.api.repository.IAlumnoRepository;
import com.shaolinquanhu.admin.api.repository.IDistritoRepository;
import com.shaolinquanhu.admin.api.repository.IProfesorDistritoRepository;
import com.shaolinquanhu.admin.api.repository.IProfesorRepository;
import com.shaolinquanhu.admin.api.repository.IUsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author dahl
 */
@Service
public class ValidationService {

    private final IAlumnoRepository alumnoRepository;
    private final IProfesorRepository profesorRepository;
    private final IDistritoRepository distritoRepository;
    private final IUsuarioRepository usuarioRepository;
    private final IProfesorDistritoRepository profDistRepository;

    @Autowired
    public ValidationService(IAlumnoRepository alumnoRepository, IProfesorRepository profesorRepository, IDistritoRepository distritoRepository, IUsuarioRepository usuarioRepository, IProfesorDistritoRepository profDistRepository) {
        this.alumnoRepository = alumnoRepository;
        this.profesorRepository = profesorRepository;
        this.distritoRepository = distritoRepository;
        this.usuarioRepository = usuarioRepository;
        this.profDistRepository = profDistRepository;
    }

    public void validateNotNull(Object obj, String msg) {
        if (obj == null) {
            throw new IllegalArgumentException(msg);
        }
    }

    public void validateAlumnoExistsById(Integer alumnoId) {
        if (alumnoId == null) {
            throw new IllegalArgumentException("El ID del alumno no puede ser null.");
        }
        if (!alumnoRepository.existsById(alumnoId)) {
            throw new EntityNotFoundException("Alumno con ID: " + alumnoId + " no fue encontrado o no existe.");
        }
    }

    public void validateProfesorExistsById(Integer profesorId) {
        if (profesorId == null) {
            throw new IllegalArgumentException("El ID del profesor no puede ser null.");
        }
        if (!profesorRepository.existsById(profesorId)) {
            throw new EntityNotFoundException("Profesor con ID: " + profesorId + " no fue encontrado o no existe.");
        }
    }

    public void validateDistritoExistsById(Integer distritoId) {
        if (distritoId == null) {
            throw new IllegalArgumentException("El ID del distrito no puede ser null.");
        }
        if (!distritoRepository.existsById(distritoId)) {
            throw new EntityNotFoundException("Distrito con ID: " + distritoId + " no fue encontrado o no existe.");
        }
    }

    public void validateUsuarioExistsById(Integer usuarioId) {
        if (usuarioId == null) {
            throw new IllegalArgumentException("El ID del usuairio no puede ser null.");
        }
        if (!usuarioRepository.existsById(usuarioId)) {
            throw new EntityNotFoundException("Usuario con ID: " + usuarioId + " no fue encontrado o no existe");
        }
    }

}
