package com.shaolinquanhu.admin.api.service;

import com.shaolinquanhu.admin.api.dto.input.UsuarioDTO;
import com.shaolinquanhu.admin.api.dto.response.UsuarioResponseDTO;
import com.shaolinquanhu.admin.api.entity.Usuario;
import com.shaolinquanhu.admin.api.repository.IProfesorRepository;
import com.shaolinquanhu.admin.api.repository.IUsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Servicio para gestionar operaciones relacionadas con usuarios. Proporciona
 * métodos para crear, leer, actualizar y eliminar usuarios, así como para
 * convertir entre entidades y DTOs.
 *
 * @author Dahl
 */
@Service
public class UsuarioService implements IConverter<Usuario, UsuarioDTO, UsuarioResponseDTO> {

    private final IUsuarioRepository uRepository;
    private final IProfesorRepository pRepository;
    private final ValidationService validator;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsuarioService(IUsuarioRepository uRepo, IProfesorRepository pRepo, ValidationService validator, PasswordEncoder passwordEncoder) {
        this.pRepository = pRepo;
        this.uRepository = uRepo;
        this.validator = validator;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Obtiene una lista de todos los usuarios.
     *
     * @return Lista de {@link UsuarioResponseDTO} que representa todos los
     * usuarios.
     */
    public List<UsuarioResponseDTO> findAll() {
        return uRepository.findAll().stream().map(this::toResponseDTO).collect(Collectors.toList());
    }

    /**
     * Obtiene una lista de todos los usuarios ordenados según el criterio
     * especificado.
     *
     * @param sort Criterio de ordenamiento.
     * @return Lista de {@link UsuarioResponseDTO} que representa todos los
     * usuarios ordenados.
     */
    public List<UsuarioResponseDTO> findAllSort(Sort sort) {
        return uRepository.findAll(sort).stream().map(this::toResponseDTO).collect(Collectors.toList());
    }

    /**
     * Busca un usuario por su ID.
     *
     * @param id ID del usuario a buscar.
     * @return {@link UsuarioResponseDTO} correspondiente al usuario encontrado.
     * @throws EntityNotFoundException Si el usuario no existe.
     */
    public UsuarioResponseDTO findById(Integer id) throws EntityNotFoundException {
        validator.validateUsuarioExistsById(id);
        return toResponseDTO(uRepository.findById(id).get());
    }

    /**
     * Guarda un nuevo usuario en la base de datos.
     *
     * @param dto DTO con la información del nuevo usuario.
     * @return {@link UsuarioResponseDTO} correspondiente al usuario guardado.
     * @throws IllegalArgumentException Si el DTO es null.
     */
    public UsuarioResponseDTO save(UsuarioDTO dto) throws IllegalArgumentException {
        validator.validateNotNull(dto, "Usuario no puede ser null.");
        var usuarioToSave = toEntity(dto);

        usuarioToSave.setContrasenia(passwordEncoder.encode(dto.getContrasenia()));

        return toResponseDTO(uRepository.save(usuarioToSave));
    }

    /**
     * Actualiza un usuario existente en la base de datos.
     *
     * @param usuarioId Usuario al que va dirijida la actualizacion.
     * @param dto DTO con la información del usuario a actualizar.
     * @return {@link UsuarioResponseDTO} correspondiente al usuario
     * actualizado.
     * @throws IllegalArgumentException Si el DTO es null.
     * @throws EntityNotFoundException Si el usuario o el profesor no se
     * encuentran.
     */
    public UsuarioResponseDTO update(Integer usuarioId, UsuarioDTO dto) throws EntityNotFoundException, IllegalArgumentException {
        validator.validateNotNull(dto, "Usuario no puede ser null.");
        validator.validateUsuarioExistsById(usuarioId);
        validator.validateProfesorExistsById(dto.getProfesorId());
        var existingUsuario = uRepository.findById(usuarioId).get();

        existingUsuario.setNombreUsuario(dto.getNombreUsuario());
        existingUsuario.setEmail(dto.getEmail());
        existingUsuario.setContrasenia(passwordEncoder.encode(dto.getContrasenia()));
        existingUsuario.setRol(dto.getRol());
        existingUsuario.setProfesor(pRepository.findById(dto.getProfesorId()).get());

        return toResponseDTO(uRepository.save(existingUsuario));
    }

    /**
     * Elimina un usuario de la base de datos por su ID.
     *
     * @param id ID del usuario a eliminar.
     * @throws EntityNotFoundException Si el usuario no existe.
     */
    public void deleteById(Integer id) throws EntityNotFoundException {
        validator.validateUsuarioExistsById(id);
        uRepository.deleteById(id);
    }

    @Override
    public Usuario toEntity(UsuarioDTO dto) {
        validator.validateNotNull(dto, "DTO no puede ser null.");
        validator.validateProfesorExistsById(dto.getProfesorId());

        var profesor = pRepository.findById(dto.getProfesorId()).get();
        var usuario = new Usuario();
        usuario.setNombreUsuario(dto.getNombreUsuario());
        usuario.setEmail(dto.getEmail());
        usuario.setContrasenia(dto.getContrasenia());
        usuario.setRol(dto.getRol());
        usuario.setProfesor(profesor);
        return usuario;
    }

    @Override
    public UsuarioResponseDTO toResponseDTO(Usuario entity) {
        validator.validateNotNull(entity, "Usuario no puede ser null.");

        var response = new UsuarioResponseDTO();
        response.setUsuarioId(entity.getUsuarioId());
        response.setNombreUsuario(entity.getNombreUsuario());
        response.setNombreProfesor(uRepository.getNombreProfesor(entity.getProfesor().getProfesorId()));
        response.setEmail(entity.getEmail());
        response.setRol(entity.getRol());
        response.setCreacion(entity.getCreacion());
        return response;
    }
}
