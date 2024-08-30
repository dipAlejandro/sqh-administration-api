package com.shaolinquanhu.admin.api.repository;

import com.shaolinquanhu.admin.api.entity.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Dahl
 */
@Repository
public interface IAlumnoRepository extends JpaRepository<Alumno, Integer> {
    
}
