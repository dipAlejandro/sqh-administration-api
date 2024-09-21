package com.shaolinquanhu.admin.api.service;

import com.shaolinquanhu.admin.api.dto.input.ProfesorDTO;
import com.shaolinquanhu.admin.api.dto.response.ProfesorResponseDTO;
import com.shaolinquanhu.admin.api.entity.Profesor;
import com.shaolinquanhu.admin.api.repository.IAlumnoRepository;
import com.shaolinquanhu.admin.api.repository.IProfesorRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * Servicio para la gestión de la entidad Profesor. Proporciona métodos para
 * crear, actualizar, eliminar y consultar profesores. Extiende la clase
 * AbstractConverter para convertir entre Profesor, ProfesorDTO y
 * ProfesorResponseDTO.
 *
 * @author Dahl
 */
@Service
public class ProfesorService implements IConverter<Profesor, ProfesorDTO, ProfesorResponseDTO> {

    private final IProfesorRepository pRepository;
    private final IAlumnoRepository aRepository;
    private final ValidationService validator;

    @Autowired
    public ProfesorService(IProfesorRepository pRepo, IAlumnoRepository aRepo, ValidationService validator) {
        this.pRepository = pRepo;
        this.aRepository = aRepo;
        this.validator = validator;
    }

    /**
     * Obtiene una lista de todos los profesores.
     *
     * @return Lista de ProfesorResponseDTO con los detalles de todos los
     * profesores.
     */
    public List<ProfesorResponseDTO> findAll() {
        var profesores = pRepository.findAll();
        var response = profesores.stream().map(this::toResponseDTO).collect(Collectors.toList());
        return response;
    }

    /**
     * Obtiene una lista de todos los profesores, ordenada según los parámetros
     * especificados.
     *
     * @param sort Parámetros de ordenamiento.
     * @return Lista ordenada de ProfesorResponseDTO con los detalles de todos
     * los profesores.
     */
    public List<ProfesorResponseDTO> findAllSort(Sort sort) {
        var profesores = pRepository.findAll(sort);
        var response = profesores.stream().map(this::toResponseDTO).collect(Collectors.toList());
        return response;
    }

    /**
     * Busca un profesor por su ID.
     *
     * @param id ID del profesor a buscar.
     * @return ProfesorResponseDTO con los detalles del profesor encontrado.
     * @throws EntityNotFoundException si no se encuentra un profesor con el ID
     * especificado.
     */
    public ProfesorResponseDTO findById(Integer id) throws EntityNotFoundException {
        validator.validateProfesorExistsById(id);
        var profesor = pRepository.findById(id).get();
        return toResponseDTO(profesor);
    }

    /**
     * Guarda un nuevo profesor en la base de datos.
     *
     * @param p ProfesorDTO con los datos del profesor a guardar.
     * @return ProfesorResponseDTO con los detalles del profesor guardado.
     * @throws IllegalArgumentException si el DTO es nulo.
     * @throws EntityNotFoundException si el alumno asociado no es encontrado.
     */
    public ProfesorResponseDTO save(ProfesorDTO p) throws IllegalArgumentException, EntityNotFoundException {
        validator.validateNotNull(p, "Profesor no puede ser null.");
        validator.validateAlumnoExistsById(p.getAlumnoId());
        var profesor = pRepository.save(toEntity(p));
        return toResponseDTO(profesor);
    }

    /**
     * Guarda un profesor en la base de datos.
     *
     * @param alumnoId ID del alumno asignado como profesor.
     * @return ProfesorResponseDTO
     */
    public ProfesorResponseDTO saveWithId(Integer alumnoId) {
        validator.validateAlumnoExistsById(alumnoId);
        var alumno = aRepository.findById(alumnoId).get();
        var profesor = new Profesor();
        profesor.setAlumno(alumno);

        return toResponseDTO(pRepository.save(profesor));
    }

    /**
     * Actualiza los detalles de un profesor existente.
     *
     * @param profesorId
     * @param dto ProfesorDTO con los datos actualizados del profesor.
     * @return ProfesorResponseDTO con los detalles actualizados del profesor.
     * @throws IllegalArgumentException si el DTO es nulo.
     * @throws EntityNotFoundException si el profesor o el alumno no existen en
     * la base de datos.
     */
    public ProfesorResponseDTO update(Integer profesorId, ProfesorDTO dto) throws IllegalArgumentException, EntityNotFoundException, EntityNotFoundException {
        validator.validateProfesorExistsById(profesorId);
        validator.validateAlumnoExistsById(dto.getAlumnoId());
        validator.validateNotNull(dto, "Profesor no puede ser null.");

        var existingProfesor = pRepository.findById(profesorId).get();
        existingProfesor.setAlumno(aRepository.findById(dto.getAlumnoId()).get());

        var profesor = pRepository.save(existingProfesor);
        return toResponseDTO(profesor);
    }

    /**
     * Elimina un profesor de la base de datos por su ID.
     *
     * @param id ID del profesor a eliminar.
     * @throws EntityNotFoundException si no se encuentra un profesor con el ID
     * especificado.
     */
    public void deleteById(Integer id) throws EntityNotFoundException {
        validator.validateProfesorExistsById(id);
        pRepository.deleteById(id);
    }

    /**
     * Convierte un DTO de profesor a una entidad Profesor.
     *
     * @param dto ProfesorDTO con los datos del profesor.
     * @return Entidad Profesor.
     * @throws EntityNotFoundException si el alumno asociado no es encontrado.
     */
    @Override
    public Profesor toEntity(ProfesorDTO dto) {
        validator.validateAlumnoExistsById(dto.getAlumnoId());
        var profesor = new Profesor();
        var alumno = aRepository.findById(dto.getAlumnoId()).get();
        profesor.setAlumno(alumno);
        return profesor;
    }

    /**
     * Convierte una entidad Profesor a un DTO de respuesta ProfesorResponseDTO.
     *
     * @param entity Entidad Profesor.
     * @return ProfesorResponseDTO con los detalles del profesor.
     */
    @Override
    public ProfesorResponseDTO toResponseDTO(Profesor entity) {
        validator.validateNotNull(entity, "Profesor no puede ser null.");
        var response = new ProfesorResponseDTO();
        response.setNombreProfesor(aRepository.getName(entity.getAlumno().getAlumnoId()));
        response.setProfesorId(entity.getProfesorId());
        return response;
    }
}
