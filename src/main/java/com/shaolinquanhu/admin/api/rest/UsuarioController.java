package com.shaolinquanhu.admin.api.rest;

import com.shaolinquanhu.admin.api.dto.input.UsuarioDTO;
import com.shaolinquanhu.admin.api.dto.response.UsuarioResponseDTO;
import com.shaolinquanhu.admin.api.service.UsuarioService;
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
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

    private final UsuarioService service;

    @Autowired
    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> getAllUsuarios(@RequestParam(required = false, defaultValue = "asc") String sort) {
        Sort.Direction direction = sort.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        // var usuarios = service.findAllSort(Sort.by(direction,"nombreUsuario"));      
        var usuarios = service.findAll();
        return ResponseEntity.ok().body(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> getUsuario(@PathVariable("id") Integer usuarioId) {
        var usuario = service.findById(usuarioId);
        return ResponseEntity.ok(usuario);
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> createUsuario(@RequestBody UsuarioDTO usuario) {
        var createdUsuario = service.save(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUsuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> updateUsuario(@PathVariable Integer id, @RequestBody UsuarioDTO usuario) {
        var updatedUsuario = service.update(id, usuario);
        return ResponseEntity.ok(updatedUsuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Integer id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
