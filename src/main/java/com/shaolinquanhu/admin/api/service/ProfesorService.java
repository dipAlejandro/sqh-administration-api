package com.shaolinquanhu.admin.api.service;

import com.shaolinquanhu.admin.api.entity.Profesor;
import com.shaolinquanhu.admin.api.repository.IProfesorRepository;
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
public class ProfesorService {

    private final IProfesorRepository repository;

    @Autowired
    public ProfesorService(IProfesorRepository repository) {
        this.repository = repository;
    }

    public List<Profesor> findAll() {
        return repository.findAll();
    }

    public List<Profesor> findAllSort(Sort sort) {
        return repository.findAll(sort);
    }

    public Optional<Profesor> findById(Integer id) {
        return repository.findById(id);
    }

    public Profesor save(Profesor p) {
        return repository.save(p);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}
