package com.shaolinquanhu.admin.api.rest;

import com.shaolinquanhu.admin.api.dto.input.ProfesorDTO;
import com.shaolinquanhu.admin.api.dto.response.ProfesorResponseDTO;
import com.shaolinquanhu.admin.api.service.ProfesorService;
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
@RequestMapping("/api/v1/profesores")
public class ProfesorController {

    private final ProfesorService profesorService;

    @Autowired
    public ProfesorController(ProfesorService profesorService) {
        this.profesorService = profesorService;
    }

    @GetMapping
    public ResponseEntity<List<ProfesorResponseDTO>> getAllProfesores(@RequestParam(required = false, defaultValue = "asc") String sort) {
        Sort.Direction direction = sort.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        var profesores = profesorService.findAllSort(Sort.by(direction, "profesorId"));
        return ResponseEntity.ok(profesores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfesorResponseDTO> getProfesor(@PathVariable("id") Integer profesorId) {
        var profesor = profesorService.findById(profesorId);
        return ResponseEntity.ok(profesor);
    }

    @PostMapping
    public ResponseEntity<ProfesorResponseDTO> createProfesor(@RequestBody ProfesorDTO dto) {
        var createdProfesor = profesorService.save(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdProfesor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfesorResponseDTO> updateProfesor(@PathVariable Integer id, @RequestBody ProfesorDTO profesorDTO) {
        var updatedProfesor = profesorService.update(id, profesorDTO);
        return ResponseEntity.ok(updatedProfesor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfesor(@PathVariable Integer id) {
        profesorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
