package com.shaolinquanhu.admin.api.service;

import com.shaolinquanhu.admin.api.dto.input.AlumnoDTO;
import com.shaolinquanhu.admin.api.dto.response.AlumnoResponseDTO;
import com.shaolinquanhu.admin.api.entity.Alumno;
import com.shaolinquanhu.admin.api.repository.IAlumnoRepository;
import com.shaolinquanhu.admin.api.repository.IDistritoRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 *
 * @author Usuario
 */
@Service
public class AlumnoService implements IConverter<Alumno, AlumnoDTO, AlumnoResponseDTO> {

    private final IAlumnoRepository aRepository;
    private final IDistritoRepository dRepository;
    private final ValidationService validator;

    @Autowired
    public AlumnoService(IAlumnoRepository aRepo, IDistritoRepository dRepo, ValidationService validator) {
        this.aRepository = aRepo;
        this.dRepository = dRepo;
        this.validator = validator;
    }

    /**
     * Obtiene una lista de todos los alumnos de la base de datos y los
     * convierte a AlumnoResponseDTO.
     *
     * @return Lista de AlumnoResponseDTO con todos los alumnos.
     */
    public List<AlumnoResponseDTO> findAll() {
        var alumnos = aRepository.findAll();
        List<AlumnoResponseDTO> response = new ArrayList<>();

        response = alumnos.stream().map(this::toResponseDTO).collect(Collectors.toList());

        return response;
    }

    /**
     * Obtiene una lista de todos los alumnos de la base de datos ordenados por
     * el criterio especificado y los convierte a AlumnoResponseDTO.
     *
     * @param sort Parámetro de ordenamiento (ascendente o descendente).
     * @return Lista de AlumnoResponseDTO con los alumnos ordenados.
     */
    public List<AlumnoResponseDTO> findAllSort(Sort sort) {

        var alumnos = aRepository.findAll(sort);

        List<AlumnoResponseDTO> response = new ArrayList<>();

        response = alumnos.stream().map(this::toResponseDTO).collect(Collectors.toList());

        return response;
    }

    /**
     * Busca un alumno por su ID y lo convierte a AlumnoResponseDTO.
     *
     * @param id ID del alumno a buscar.
     * @return AlumnoResponseDTO correspondiente al alumno encontrado.
     * @throws EntityNotFoundException Si el alumno no se encuentra en la base
     * de datos.
     */
    public AlumnoResponseDTO findById(Integer alumnoId) throws EntityNotFoundException {

        validator.validateAlumnoExistsById(alumnoId);

        var alumno = aRepository.findById(alumnoId).get();

        return toResponseDTO(alumno);
    }

    /**
     * Guarda un nuevo alumno en la base de datos. Convierte el DTO recibido en
     * una entidad antes de guardarlo.
     *
     * @param a DTO con la información del nuevo alumno.
     * @return AlumnoResponseDTO correspondiente al alumno guardado.
     * @Throws IllegalArgumentException si el alumno ya existe en la base de
     * datos.
     */
    public AlumnoResponseDTO save(AlumnoDTO dto) throws IllegalArgumentException {
        validator.validateNotNull(dto, "Alumno no puede ser null.");
        var alumnoToSave = toEntity(dto);
        var alumno = aRepository.save(alumnoToSave);

        return toResponseDTO(alumno);
    }

    /**
     * Actualiza un alumno existente en la base de datos. Convierte el DTO
     * recibido en una entidad antes de actualizarlo.
     *
     * @param alumnoId ID del alumno que se va a actualzar
     * @param dto alumno actualizado
     * @return AlumnoResponseDTO correspondiente al alumno actualizado.
     * @throws IllegalArgumentException Si el alumno no existe en la base de
     * datos.
     */
    public AlumnoResponseDTO update(Integer alumnoId, AlumnoDTO dto) throws IllegalArgumentException {
        validator.validateAlumnoExistsById(alumnoId);
        validator.validateNotNull(dto, "Alumno no puede ser null.");

        var existingAlumno = aRepository.findById(alumnoId).get();

        // Actualizar los campos del alumno con los valores del DTO
        existingAlumno.setNombres(dto.getNombres());
        existingAlumno.setApellidos(dto.getApellidos());
        existingAlumno.setDni(dto.getDni());
        existingAlumno.setCategoria(dto.getCategoria());
        existingAlumno.setComentarios(dto.getComentarios());
        existingAlumno.setDireccion(dto.getDireccion());
        existingAlumno.setTelefono(dto.getTelefono());
        existingAlumno.setFechaNacimiento(dto.getFechaNacimiento());

        var response = toResponseDTO(aRepository.save(existingAlumno));
        return response;
    }

    /**
     * Elimina un alumno de la base de datos por su ID.
     *
     * @param id ID del alumno a eliminar.
     * @throws EntityNotFoundException Si el alumno no se encuentra en la base
     * de datos.
     */
    public void deleteById(Integer id) throws EntityNotFoundException {
        validator.validateAlumnoExistsById(id);
        aRepository.deleteById(id);
    }

    /**
     * Convierte un AlumnoDTO a una entidad Alumno para su persistencia.
     *
     * @param dto DTO con la información del alumno.
     * @return Entidad Alumno.
     * @throws EntityNotFoundException Si el distrito relacionado al alumno no
     * existe en la base de datos.
     */
    @Override
    public Alumno toEntity(AlumnoDTO dto) {
        validator.validateNotNull(dto, "Alumno no puede ser null.");
        validator.validateDistritoExistsById(dto.getDistritoId());

        var alumno = new Alumno();
        var distrito = dRepository.findById(dto.getDistritoId()).get();

        alumno.setNombres(dto.getNombres());
        alumno.setApellidos(dto.getApellidos());
        alumno.setFechaNacimiento(dto.getFechaNacimiento());
        alumno.setDni(dto.getDni());
        alumno.setTelefono(dto.getTelefono());
        alumno.setDireccion(dto.getDireccion());
        alumno.setCategoria(dto.getCategoria());
        alumno.setDistrito(distrito);
        alumno.setComentarios(dto.getComentarios());

        return alumno;
    }

    /**
     * Convierte una entidad Alumno a un AlumnoResponseDTO para su envío como
     * respuesta.
     *
     * @param alumno Entidad Alumno.
     * @return DTO con la información del alumno para la respuesta.
     */
    @Override
    public AlumnoResponseDTO toResponseDTO(Alumno alumno) {
        validator.validateNotNull(alumno, "Alumno no puede ser null.");
        var response = new AlumnoResponseDTO();

        var nombreDistrito = dRepository.getName(alumno.getDistrito().getDistritoId());
        response.setNombres(alumno.getNombres());
        response.setApellidos(alumno.getApellidos());
        response.setFechaNacimiento(alumno.getFechaNacimiento());
        response.setDni(alumno.getDni());
        response.setTelefono(alumno.getTelefono());
        response.setDireccion(alumno.getDireccion());
        response.setCategoria(alumno.getCategoria());
        response.setNombreDistrito(nombreDistrito);
        response.setComentarios(alumno.getComentarios());

        return response;
    }

}
