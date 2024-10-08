package com.shaolinquanhu.admin.api.repository;

import com.shaolinquanhu.admin.api.entity.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Dahl
 */
@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {

    @Query("SELECT a.nombres FROM Profesor p JOIN p.alumno a WHERE p.profesorId = :profesorId")
    public String getNombreProfesor(@Param("profesorId") Integer profesorId);
    
    @Query("SELECT u FROM Usuario u WHERE u.nombreUsuario = :nombre")
    public Optional<Usuario> findByNombreUsuario(@Param("nombre") String nombre);
}
