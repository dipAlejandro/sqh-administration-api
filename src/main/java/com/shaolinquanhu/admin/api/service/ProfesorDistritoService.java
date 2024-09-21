package com.shaolinquanhu.admin.api.service;

import com.shaolinquanhu.admin.api.dto.input.ProfesorDistritoDTO;
import com.shaolinquanhu.admin.api.dto.response.DistritoResponseDTO;
import com.shaolinquanhu.admin.api.dto.response.ProfesorDistritoResponseDTO;
import com.shaolinquanhu.admin.api.dto.response.ProfesorResponseDTO;
import com.shaolinquanhu.admin.api.entity.ProfesorDistrito;
import com.shaolinquanhu.admin.api.entity.ProfesorDistritoId;
import com.shaolinquanhu.admin.api.repository.IAlumnoRepository;
import com.shaolinquanhu.admin.api.repository.IDistritoRepository;
import com.shaolinquanhu.admin.api.repository.IProfesorDistritoRepository;
import com.shaolinquanhu.admin.api.repository.IProfesorRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servicio para manejar la lógica de negocio relacionada con la asociación
 * entre Profesor y Distrito.
 */
@Service
public class ProfesorDistritoService implements IConverter<ProfesorDistrito, ProfesorDistritoDTO, ProfesorDistritoResponseDTO> {

    private final IProfesorDistritoRepository pdRepository;
    private final IProfesorRepository pRepository;
    private final IDistritoRepository dRepository;
    private final IAlumnoRepository aRepository;
    private final DistritoService distritoService;
    private final ProfesorService profesorService;
    private final ValidationService validator;

    @Autowired
    public ProfesorDistritoService(IProfesorDistritoRepository pdRepo, IProfesorRepository pRepo, IDistritoRepository dRepo, IAlumnoRepository aRepo, DistritoService dService, ProfesorService pService, ValidationService validator) {
        this.pdRepository = pdRepo;
        this.pRepository = pRepo;
        this.dRepository = dRepo;
        this.aRepository = aRepo;
        this.distritoService = dService;
        this.profesorService = pService;
        this.validator = validator;
    }

    /**
     * Recupera todos los registros de ProfesorDistrito de la base de datos y
     * los convierte en DTOs de respuesta.
     *
     * @return Una lista de {@link ProfesorDistritoResponseDTO} que representa
     * todas las asignaciones de profesores a distritos.
     */
    public List<ProfesorDistritoResponseDTO> findAll() {
        var list = pdRepository.findAll();
        return list.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    /**
     * Guarda un nuevo registro de ProfesorDistrito en la base de datos, después
     * de validar que el profesor y el distrito existen, y que la asignación no
     * exista ya.
     *
     * @param pdId Identificador compuesto que incluye los IDs del profesor y
     * del distrito.
     * @return El DTO de respuesta con la información del nuevo registro
     * guardado.
     * @throws EntityNotFoundException si el profesor o el distrito no existen,
     * o si ya existe la asignación.
     * @throws IllegalArgumentException si el ProfesorDistritoId es null.
     */
    public ProfesorDistritoResponseDTO save(ProfesorDistritoId pdId) throws EntityNotFoundException, IllegalArgumentException {
        validator.validateNotNull(pdId, "La clave compuesta no puede ser null.");
        validator.validateProfesorExistsById(pdId.getProfesorId());
        validator.validateDistritoExistsById(pdId.getDistritoId());
        if (pdRepository.existsByCompositeKey(pdId.getDistritoId(), pdId.getProfesorId())) {
            throw new IllegalArgumentException("Profesor con ID: " + pdId.getProfesorId() + " ya fue asignado al Distrito con ID: " + pdId.getDistritoId());
        }

        var profesor = pRepository.findById(pdId.getProfesorId()).get();
        var distrito = dRepository.findById(pdId.getDistritoId()).get();

        var profesorDistrito = new ProfesorDistrito();
        profesorDistrito.setId(pdId);
        profesorDistrito.setProfesor(profesor);
        profesorDistrito.setDistrito(distrito);

        pdRepository.save(profesorDistrito);

        return toResponseDTO(profesorDistrito);
    }

    /**
     * Busca un registro de ProfesorDistrito por su clave compuesta.
     *
     * @param pdId Clave compuesta que incluye los IDs del profesor y del
     * distrito.
     * @return El DTO de respuesta correspondiente al registro encontrado.
     * @throws EntityNotFoundException si no se encuentra el registro.
     * @throws IllegalArgumentException si el ProfesorDistritoId es null.
     */
    public ProfesorDistritoResponseDTO findById(ProfesorDistritoId pdId) throws EntityNotFoundException, IllegalArgumentException {
        validator.validateNotNull(pdId, "La clave compuesta no puede ser null.");
        var profesorDistrito = pdRepository.findById(pdId)
                .orElseThrow(() -> new EntityNotFoundException("No existe Profesor con ID: " + pdId.getProfesorId() + " asignado a Distrito con ID: " + pdId.getDistritoId()));
        return toResponseDTO(profesorDistrito);
    }

    /**
     * Elimina un registro de ProfesorDistrito por su clave compuesta.
     *
     * @param pdId Clave compuesta que incluye los IDs del profesor y del
     * distrito.
     * @throws IllegalArgumentException si el ProfesorDistritoId es null.
     * @throws EntityNotFoundException si el registro no se encuentra.
     */
    public void deleteByCompositeId(ProfesorDistritoId pdId) throws EntityNotFoundException, IllegalArgumentException {
        validator.validateNotNull(pdId, "Clave compuesta no puede ser null.");
        if (!pdRepository.existsByCompositeKey(pdId.getDistritoId(), pdId.getProfesorId())) {
            throw new EntityNotFoundException("No existe Profesor con ID: " + pdId.getProfesorId() + " asignado a Distrito con ID: " + pdId.getDistritoId());
        }

        pdRepository.deleteById(pdId);
    }

    /**
     * Busca todos los distritos a los que un profesor ha sido asignado.
     *
     * @param profesorId ID del profesor a buscar.
     * @return Una lista de {@link DistritoResponseDTO} que representa los
     * distritos del profesor.
     * @throws EntityNotFoundException si el profesor no existe.
     */
    public List<DistritoResponseDTO> findByProfesorId(Integer profesorId) throws EntityNotFoundException {
        validator.validateProfesorExistsById(profesorId);

        return pdRepository.findDistritosByProfesorId(profesorId)
                .stream().map(distritoService::toResponseDTO)
                .collect(Collectors.toList());
    }

    /**
     * Busca todos los profesores asignados a un distrito específico.
     *
     * @param distritoId ID del distrito a buscar.
     * @return Una lista de {@link ProfesorResponseDTO} que representa los
     * profesores en ese distrito.
     * @throws EntityNotFoundException si el distrito no existe.
     */
    public List<ProfesorResponseDTO> findByDistritoId(Integer distritoId) throws EntityNotFoundException {
        validator.validateDistritoExistsById(distritoId);
        return pdRepository.findProfesoresByDistritoId(distritoId)
                .stream().map(profesorService::toResponseDTO)
                .collect(Collectors.toList());
    }

    /**
     * Convierte un DTO de ProfesorDistrito a una entidad ProfesorDistrito.
     *
     * @param dto DTO que contiene la información de la asignación.
     * @return La entidad ProfesorDistrito correspondiente.
     */
    @Override
    public ProfesorDistrito toEntity(ProfesorDistritoDTO dto) {
        var profesorDistrito = new ProfesorDistrito();
        validator.validateDistritoExistsById(dto.getDistritoId());
        validator.validateProfesorExistsById(dto.getProfesorId());

        var distrito = dRepository.findById(dto.getDistritoId()).get();
        var profesor = pRepository.findById(dto.getProfesorId()).get();
        profesorDistrito.setId(new ProfesorDistritoId(dto.getProfesorId(), dto.getDistritoId()));
        profesorDistrito.setDistrito(distrito);
        profesorDistrito.setProfesor(profesor);
        return profesorDistrito;
    }

    /**
     * Convierte una entidad ProfesorDistrito a un DTO de respuesta.
     *
     * @param entity Entidad ProfesorDistrito que se va a convertir.
     * @return El DTO de respuesta que representa la entidad.
     */
    @Override
    public ProfesorDistritoResponseDTO toResponseDTO(ProfesorDistrito entity) {
        var response = new ProfesorDistritoResponseDTO();
        response.setNombreDistrito(dRepository.getName(entity.getDistrito().getDistritoId()));
        response.setNombreProfesor(aRepository.getName(entity.getProfesor().getAlumno().getAlumnoId()));
        return response;
    }
}
