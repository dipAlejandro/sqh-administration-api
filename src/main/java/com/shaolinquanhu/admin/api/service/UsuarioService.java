package com.shaolinquanhu.admin.api.service;

import com.shaolinquanhu.admin.api.entity.Usuario;
import com.shaolinquanhu.admin.api.repository.IUsuarioRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 *
 * @author Dahl
 */
@Service
public class UsuarioService {
    private final IUsuarioRepository repository;

    @Autowired
    public UsuarioService(IUsuarioRepository repository) {
        this.repository = repository;
    }
    
    public List<Usuario> findAll() {
        return repository.findAll();
    }

    public List<Usuario> findAllSort(Sort sort) {
        return repository.findAll(sort);
    }

    public Optional<Usuario> findById(Integer id) {
        return repository.findById(id);
    }

    public Usuario save(Usuario u) {
        return repository.save(u);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}
