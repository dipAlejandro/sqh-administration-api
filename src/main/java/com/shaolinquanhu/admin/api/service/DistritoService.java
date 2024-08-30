package com.shaolinquanhu.admin.api.service;

import com.shaolinquanhu.admin.api.entity.Distrito;
import com.shaolinquanhu.admin.api.repository.IDistritoRepository;
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
public class DistritoService {

    private final IDistritoRepository repository;

    @Autowired
    public DistritoService(IDistritoRepository repository) {
        this.repository = repository;
    }

    public List<Distrito> findAll() {
        return repository.findAll();
    }

    public List<Distrito> findAllSort(Sort sort) {
        return repository.findAll(sort);
    }

    public Optional<Distrito> findById(Integer id) {
        return repository.findById(id);
    }

    public Distrito save(Distrito d) {
        return repository.save(d);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}
