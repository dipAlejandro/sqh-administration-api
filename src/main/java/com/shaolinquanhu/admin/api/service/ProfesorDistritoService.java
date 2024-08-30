package com.shaolinquanhu.admin.api.service;

import com.shaolinquanhu.admin.api.entity.ProfesorDistrito;
import com.shaolinquanhu.admin.api.entity.ProfesorDistritoId;
import com.shaolinquanhu.admin.api.repository.IProfesorDistritoRepository;
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
public class ProfesorDistritoService {

    private final IProfesorDistritoRepository repository;

    @Autowired
    public ProfesorDistritoService(IProfesorDistritoRepository repository) {
        this.repository = repository;
    }

    public List<ProfesorDistrito> findAll() {
        return repository.findAll();
    }

    public List<ProfesorDistrito> findAllSort(Sort sort) {
        return repository.findAll(sort);
    }

    public Optional<ProfesorDistrito> findById(ProfesorDistritoId id) {
        return repository.findById(id);
    }

    public ProfesorDistrito save(ProfesorDistrito pd) {
        return repository.save(pd);
    }

    public void deleteById(ProfesorDistritoId id) {
        repository.deleteById(id);
    }

}
