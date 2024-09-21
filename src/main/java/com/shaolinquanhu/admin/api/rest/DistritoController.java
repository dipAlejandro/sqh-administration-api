package com.shaolinquanhu.admin.api.rest;

import com.shaolinquanhu.admin.api.dto.input.DistritoDTO;
import com.shaolinquanhu.admin.api.dto.response.DistritoResponseDTO;
import com.shaolinquanhu.admin.api.service.DistritoService;
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
 * @author Dahl
 */
@RestController
@RequestMapping("/api/v1/distritos")
public class DistritoController {

    private final DistritoService service;

    public DistritoController(DistritoService ss) {
        this.service = ss;
    }

    @GetMapping
    public ResponseEntity<List<DistritoResponseDTO>> getAllDistritos(@RequestParam(required = false, defaultValue = "asc") String sort) {
        Sort.Direction direction = sort.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;

        var distritos = service.findAllSort(Sort.by(direction, "nombre"));
        return ResponseEntity.ok(distritos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DistritoResponseDTO> getDistrito(@PathVariable("id") Integer id) {
        var response = service.findById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<DistritoResponseDTO> createDistrito(@RequestBody DistritoDTO distritoDTO) {
        var distritoResponse = service.save(distritoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(distritoResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DistritoResponseDTO> updateDistrito(@PathVariable("id") Integer distritoId, @RequestBody DistritoDTO distritoDTO) {
        var updatedDistrito = service.update(distritoId, distritoDTO);
        return ResponseEntity.ok(updatedDistrito);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDistrito(@PathVariable Integer id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
