package com.shaolinquanhu.admin.api.repository;

import com.shaolinquanhu.admin.api.entity.Distrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Dahl
 */
@Repository
public interface IDistritoRepository extends JpaRepository<Distrito, Integer> {
    
}
