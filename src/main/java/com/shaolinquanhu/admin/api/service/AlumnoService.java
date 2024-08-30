package com.shaolinquanhu.admin.api.service;

import com.shaolinquanhu.admin.api.entity.Alumno;
import com.shaolinquanhu.admin.api.repository.IAlumnoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 *
 * @author Usuario
 */
@Service
public class AlumnoService {

    private final IAlumnoRepository repository;

    @Autowired
    public AlumnoService(IAlumnoRepository repository) {
        this.repository = repository;
    }

    public List<Alumno> findAll() {
        return repository.findAll();
    }

    public List<Alumno> findAllSort(Sort sort) {
        return repository.findAll(sort);
    }

    public Optional<Alumno> findById(Integer id) {
        return repository.findById(id);
    }

    public Alumno save(Alumno a) {
        return repository.save(a);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

}
