package com.shaolinquanhu.admin.api.repository;

import com.shaolinquanhu.admin.api.entity.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Usuario
 */
@Repository
public interface IProfesorRepository extends JpaRepository<Profesor, Integer> {

}
