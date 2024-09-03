package com.shaolinquanhu.admin.api.rest;

import com.shaolinquanhu.admin.api.entity.Alumno;
import com.shaolinquanhu.admin.api.service.AlumnoService;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Usuario
 */
@RestController
@RequestMapping("/api/v1/alumnos")
public class AlumnosController {

    private final AlumnoService alumnoService;

    public AlumnosController(AlumnoService alumnoService) {
        this.alumnoService = alumnoService;
    }

    @GetMapping
    public ResponseEntity<List<Alumno>> getAllAlumnos(@RequestParam(required = false, defaultValue = "asc") String sort) {
        Sort.Direction direction = sort.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        var alumnos = alumnoService.findAllSort(Sort.by(direction, "nombres"));
        return ResponseEntity.ok(alumnos);
    }

    @PostMapping
    public ResponseEntity<Alumno> createAlumno(@RequestBody Alumno alumno) {
        var createdAlumno = alumnoService.save(alumno);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAlumno);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Alumno> updateAlumno(@PathVariable Integer id, @RequestBody Alumno alumno) {
        // Asegurarse de que el id en la URL coincide con el id en el cuerpo
        if (!id.equals(alumno.getAlumnoId())) {
            return ResponseEntity.badRequest().build();
        }
        var updatedAlumno = alumnoService.save(alumno);
        return ResponseEntity.ok(updatedAlumno);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlumno(@PathVariable Integer id) {
        alumnoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
