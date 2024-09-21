package com.shaolinquanhu.admin.api.rest;

import com.shaolinquanhu.admin.api.dto.input.AlumnoDTO;
import com.shaolinquanhu.admin.api.dto.response.AlumnoResponseDTO;
import com.shaolinquanhu.admin.api.service.AlumnoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @author Dahl
 */
@RestController
@RequestMapping("/api/v1/alumnos")
public class AlumnosController {

    private final AlumnoService alumnoService;

    @Autowired
    public AlumnosController(AlumnoService alumnoService) {
        this.alumnoService = alumnoService;
    }

    @GetMapping
    public ResponseEntity<List<AlumnoResponseDTO>> getAllAlumnos(@RequestParam(required = false, defaultValue = "asc") String sort) {
        Sort.Direction direction = sort.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        var alumnos = alumnoService.findAllSort(Sort.by(direction, "nombres"));
        return ResponseEntity.ok(alumnos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlumnoResponseDTO> getAlumno(@PathVariable("id") Integer id) {
        var alumnoResponse = alumnoService.findById(id);
        return ResponseEntity.ok(alumnoResponse);
    }

    @PostMapping
    public ResponseEntity<AlumnoResponseDTO> createAlumno(@RequestBody AlumnoDTO alumnoDTO) {
        var createdAlumno = alumnoService.save(alumnoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAlumno);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlumnoResponseDTO> updateAlumno(@PathVariable Integer id, @RequestBody AlumnoDTO alumnoDTO) {
        var updatedAlumno = alumnoService.update(id, alumnoDTO);
        return ResponseEntity.ok(updatedAlumno);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlumno(@PathVariable Integer id) {
        alumnoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
