package com.shaolinquanhu.admin.api.repository;

import com.shaolinquanhu.admin.api.entity.ProfesorDistrito;
import com.shaolinquanhu.admin.api.entity.ProfesorDistritoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Dahl
 */
@Repository
public interface IProfesorDistritoRepository extends JpaRepository<ProfesorDistrito, ProfesorDistritoId> {

}
