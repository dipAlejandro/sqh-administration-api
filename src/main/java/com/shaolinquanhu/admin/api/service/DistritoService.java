package com.shaolinquanhu.admin.api.service;

import com.shaolinquanhu.admin.api.dto.input.DistritoDTO;
import com.shaolinquanhu.admin.api.dto.response.DistritoResponseDTO;
import com.shaolinquanhu.admin.api.entity.Distrito;
import com.shaolinquanhu.admin.api.repository.IDistritoRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 *
 * @author Dahl
 */
@Service
public class DistritoService implements IConverter<Distrito, DistritoDTO, DistritoResponseDTO> {

    private final IDistritoRepository dRepository;
    private final ValidationService validator;

    @Autowired
    public DistritoService(IDistritoRepository drepo, ValidationService validator) {
        this.dRepository = drepo;
        this.validator = validator;
    }

    /**
     * Obtiene una lista de todos los distritos de la base de datos y los
     * convierte a DistritoResponseDTO.
     *
     * @return Lista de DistritoResponseDTO con todos los distritos.
     */
    public List<DistritoResponseDTO> findAll() {

        List<DistritoResponseDTO> response = new ArrayList<>();

        for (Distrito distrito : dRepository.findAll()) {
            response.add(toResponseDTO(distrito));
        }

        return response;
    }

    /**
     * Obtiene una lista de todos los distritos de la base de datos ordenados
     * por el criterio especificado y los convierte a DistritoResponseDTO.
     *
     * @param sort Parámetro de ordenamiento (ascendente o descendente).
     * @return Lista de DistritoResponseDTO con los distritos ordenados.
     */
    public List<DistritoResponseDTO> findAllSort(Sort sort) {
        List<DistritoResponseDTO> response = new ArrayList<>();

        for (Distrito distrito : dRepository.findAll(sort)) {
            response.add(toResponseDTO(distrito));
        }
        return response;
    }

    /**
     * Busca un distrito por su ID y lo convierte a DistritoResponseDTO.
     *
     * @param id ID del distrito a buscar.
     * @return DistritoResponseDTO correspondiente al distrito encontrado.
     * @throws EntityNotFoundException Si el distrito no se encuentra en la base
     * de datos.
     */
    public DistritoResponseDTO findById(Integer id) throws EntityNotFoundException {
        validator.validateDistritoExistsById(id);
        var distrito = dRepository.findById(id).get();

        return toResponseDTO(distrito);
    }

    /**
     * Guarda un nuevo distrito en la base de datos. Convierte el DTO recibido
     * en una entidad antes de guardarlo.
     *
     * @param dto DTO con la información del nuevo distrito.
     * @return DistritoResponseDTO correspondiente al distrito guardado.
     * @throws IllegalArgumentException Si ya existe un distrito con el ID
     * proporcionado.
     */
    public DistritoResponseDTO save(DistritoDTO dto) throws IllegalArgumentException {
        validator.validateNotNull(dto, "Distrito no puede ser null.");
        var entity = toEntity(dto);

        return toResponseDTO(dRepository.save(entity));
    }

    /**
     * Actualiza un distrito existente en la base de datos. Convierte el DTO
     * recibido en una entidad antes de actualizarlo.
     *
     * @param distritoId ID del distrito que se va a actualizar.
     * @param dto DTO con la información actualizada del distrito.
     * @return DistritoResponseDTO correspondiente al distrito actualizado.
     * @throws IllegalArgumentException Si el distrito no existe en la base de
     * datos.
     */
    public DistritoResponseDTO update(Integer distritoId, DistritoDTO dto) throws IllegalArgumentException, EntityNotFoundException {

        validator.validateDistritoExistsById(distritoId);
        validator.validateNotNull(dto, "Distrito no puede ser null.");
        var existingDistrito = dRepository.findById(distritoId).get();
        existingDistrito.setNombre(dto.getNombre());
        existingDistrito.setDireccion(dto.getDireccion());
        return toResponseDTO(dRepository.save(existingDistrito));

    }

    /**
     * Elimina un distrito de la base de datos por su ID.
     *
     * @param id ID del distrito a eliminar.
     * @throws EntityNotFoundException Si el distrito no se encuentra en la base
     * de datos.
     */
    public void deleteById(Integer id) throws EntityNotFoundException {
        validator.validateDistritoExistsById(id);
        dRepository.deleteById(id);
    }

    /**
     * Obtiene el nombre de un distrito por su ID.
     *
     * @param id ID del distrito.
     * @return Nombre del distrito.
     * @throws EntityNotFoundException Si el distrito no se encuentra en la base
     * de datos.
     */
    public String getDistritoName(Integer id) throws EntityNotFoundException {
        validator.validateDistritoExistsById(id);
        return dRepository.getName(id);
    }

    /**
     * Convierte un DistritoDTO a una entidad Distrito para su persistencia.
     *
     * @param dto DTO con la información del distrito.
     * @return Entidad Distrito.
     */
    @Override
    public Distrito toEntity(DistritoDTO dto) {
        var distrito = new Distrito();

        distrito.setNombre(dto.getNombre());
        distrito.setDireccion(dto.getDireccion());
        distrito.setCreacion(dto.getCreacion());

        return distrito;
    }

    /**
     * Convierte una entidad Distrito a un DistritoResponseDTO para su envío
     * como respuesta.
     *
     * @param entity Entidad Distrito.
     * @return DTO con la información del distrito para la respuesta.
     */
    @Override
    public DistritoResponseDTO toResponseDTO(Distrito entity) {
        var response = new DistritoResponseDTO();

        response.setDistritoId(entity.getDistritoId());
        response.setNombre(entity.getNombre());
        response.setDireccion(entity.getDireccion());
        response.setCreacion(entity.getCreacion());

        return response;
    }

}
