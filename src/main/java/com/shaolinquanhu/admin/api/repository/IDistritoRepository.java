package com.shaolinquanhu.admin.api.repository;

import com.shaolinquanhu.admin.api.entity.Distrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Dahl
 */
@Repository
public interface IDistritoRepository extends JpaRepository<Distrito, Integer> {
    
    @Query("SELECT d.nombre FROM Distrito d WHERE d.distritoId = :id")
    public String getName(@Param("id") Integer id);
}
