package com.shaolinquanhu.admin.api.rest;

import com.shaolinquanhu.admin.api.entity.Distrito;
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
 * @author Usuario
 */
@RestController
@RequestMapping("/api/v1/distritos")
public class DistritoController {

    private final DistritoService service;

    public DistritoController(DistritoService ss) {
        this.service = ss;
    }

    @GetMapping
    public ResponseEntity<List<Distrito>> getAllDistritos(@RequestParam(required = false, defaultValue = "asc") String sort) {
        Sort.Direction direction = sort.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;

        var distritos = service.findAllSort(Sort.by(direction, "nombre"));
        return ResponseEntity.ok(distritos);
    }

    @PostMapping
    public ResponseEntity<Distrito> createDistrito(@RequestBody Distrito distrito) {
        var createdDistrito = service.save(distrito);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDistrito);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Distrito> updateDistrito(@PathVariable("id") Integer id, @RequestBody Distrito distrito) {
        if (!id.equals(distrito.getDistritoId())) {
            return ResponseEntity.badRequest().build();
        }
        var updatedDistrito = service.save(distrito);
        return ResponseEntity.ok(updatedDistrito);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDistrito(@PathVariable Integer id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
