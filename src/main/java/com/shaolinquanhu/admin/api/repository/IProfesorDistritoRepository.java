package com.shaolinquanhu.admin.api.repository;

import com.shaolinquanhu.admin.api.entity.Distrito;
import com.shaolinquanhu.admin.api.entity.Profesor;
import com.shaolinquanhu.admin.api.entity.ProfesorDistrito;
import com.shaolinquanhu.admin.api.entity.ProfesorDistritoId;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Dahl
 */
@Repository
public interface IProfesorDistritoRepository extends JpaRepository<ProfesorDistrito, ProfesorDistritoId> {

    @Query("SELECT pd.distrito FROM ProfesorDistrito pd WHERE pd.profesor.id = :profesorId")
    List<Distrito> findDistritosByProfesorId(Integer profesorId);

    @Query("SELECT pd.profesor FROM ProfesorDistrito pd WHERE pd.distrito.id = :distritoId")
    List<Profesor> findProfesoresByDistritoId(Integer distritoId);
    
    @Query("DELETE FROM ProfesorDistrito pd WHERE pd.id.profesorId = :profesorId AND pd.id.distritoId = :distritoId")
    void deleteByProfesorIdAndDistritoId(@Param("profesorId") Integer profesorId, @Param("distritoId") Integer distritoId);
}
