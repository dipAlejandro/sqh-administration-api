package com.shaolinquanhu.admin.api.repository;

import com.shaolinquanhu.admin.api.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Dahl
 */
@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Integer>{
    
}
