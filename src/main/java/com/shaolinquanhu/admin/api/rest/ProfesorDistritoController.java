package com.shaolinquanhu.admin.api.rest;

import com.shaolinquanhu.admin.api.dto.response.DistritoResponseDTO;
import com.shaolinquanhu.admin.api.dto.response.ProfesorDistritoResponseDTO;
import com.shaolinquanhu.admin.api.dto.response.ProfesorResponseDTO;
import com.shaolinquanhu.admin.api.entity.ProfesorDistritoId;
import com.shaolinquanhu.admin.api.service.ProfesorDistritoService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador para manejar las operaciones relacionadas con la asociación entre
 * Profesor y Distrito.
 */
@RestController
@RequestMapping("/api/v1/profesores-distritos")
public class ProfesorDistritoController {

    private final ProfesorDistritoService profesorDistritoService;

    public ProfesorDistritoController(ProfesorDistritoService profesorDistritoService) {
        this.profesorDistritoService = profesorDistritoService;
    }

    // Obtener todas las asignaciones de profesores a distritos
    @GetMapping
    public ResponseEntity<List<ProfesorDistritoResponseDTO>> getAllProfesorDistrito() {
        var asignaciones = profesorDistritoService.findAll();
        return ResponseEntity.ok(asignaciones);
    }

    // Obtener todos los distritos asignados a un profesor específico
    @GetMapping("/profesor/{profesorId}")
    public ResponseEntity<List<DistritoResponseDTO>> getDistritosByProfesor(@PathVariable Integer profesorId) {
        var distritos = profesorDistritoService.findByProfesorId(profesorId);
        return ResponseEntity.ok(distritos);
    }

    // Obtener todos los profesores asignados a un distrito específico
    @GetMapping("/distrito/{distritoId}")
    public ResponseEntity<List<ProfesorResponseDTO>> getProfesoresByDistrito(@PathVariable Integer distritoId) {
        var profesores = profesorDistritoService.findByDistritoId(distritoId);
        return ResponseEntity.ok(profesores);
    }

    // Asignar un profesor a un distrito
    @PostMapping
    public ResponseEntity<ProfesorDistritoResponseDTO> assignProfesorToDistrito(@RequestBody ProfesorDistritoId pdId) {

        var nuevaAsignacion = profesorDistritoService.save(pdId);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaAsignacion);
    }

    // Eliminar la asignación de un profesor a un distrito
    @DeleteMapping("/profesor/{profesorId}/distrito/{distritoId}")
    public ResponseEntity<Void> deleteAsignacion(@PathVariable Integer profesorId, @PathVariable Integer distritoId) {
        profesorDistritoService.deleteByCompositeId(new ProfesorDistritoId(profesorId, distritoId));
        return ResponseEntity.noContent().build();
    }
}
