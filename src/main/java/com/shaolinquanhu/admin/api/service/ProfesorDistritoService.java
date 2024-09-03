package com.shaolinquanhu.admin.api.service;

import com.shaolinquanhu.admin.api.entity.Distrito;
import com.shaolinquanhu.admin.api.entity.Profesor;
import com.shaolinquanhu.admin.api.entity.ProfesorDistrito;
import com.shaolinquanhu.admin.api.entity.ProfesorDistritoId;
import com.shaolinquanhu.admin.api.repository.IProfesorDistritoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servicio para manejar la lógica de negocio relacionada con la asociación entre Profesor y Distrito.
 */
@Service
public class ProfesorDistritoService {

    private final IProfesorDistritoRepository repository;

    @Autowired
    public ProfesorDistritoService(IProfesorDistritoRepository repository) {
        this.repository = repository;
    }

    // Obtener todas las asignaciones de profesores a distritos
    public List<ProfesorDistrito> findAll() {
        return repository.findAll();
    }

    // Guardar una nueva asignación de profesor a distrito
    public ProfesorDistrito save(ProfesorDistrito pd) {
        return repository.save(pd);
    }

    // Encontrar una asignación específica por su ID compuesto
    public Optional<ProfesorDistrito> findById(ProfesorDistritoId id) {
        return repository.findById(id);
    }

    // Eliminar una asignación específica por su ID compuesto
    public void deleteById(ProfesorDistritoId id) {
        repository.deleteById(id);
    }

    // Eliminar una asignación específica usando profesorId y distritoId
    public void deleteByProfesorIdAndDistritoId(Integer profesorId, Integer distritoId) {
        repository.deleteByProfesorIdAndDistritoId(profesorId, distritoId);
    }

    // Obtener todos los distritos asignados a un profesor específico
    public List<Distrito> findByProfesorId(Integer profesorId) {
        return repository.findDistritosByProfesorId(profesorId);
    }

    // Obtener todos los profesores asignados a un distrito específico
    public List<Profesor> findByDistritoId(Integer distritoId) {
        return repository.findProfesoresByDistritoId(distritoId);
    }
}
