package com.shaolinquanhu.admin.api.repository;

import com.shaolinquanhu.admin.api.entity.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Dahl
 */
@Repository
public interface IAlumnoRepository extends JpaRepository<Alumno, Integer> {

    @Query("SELECT CONCAT(a.nombres, ' ', a.apellidos) FROM Alumno a WHERE a.alumnoId = :alumnoId")
    public String getFullName(@Param("alumnoId") Integer alumnoId);

}
