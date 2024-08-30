package com.shaolinquanhu.admin.api.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 *
 * @author Dahl
 */
@Entity
@Table(name = "profesor")
public class Profesor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profesor_id", nullable = false)
    private Integer profesorId;
    @OneToOne
    @JoinColumn(name = "fk_alumno", nullable = true)
    private Alumno alumno;
    @Column
    private LocalDateTime creacion;

    public Profesor() {
    }

    public Profesor(Integer profesorId, Alumno alumno, LocalDateTime creacion) {
        this.profesorId = profesorId;
        this.alumno = alumno;
        this.creacion = creacion;
    }

    public Integer getProfesorId() {
        return profesorId;
    }

    public void setProfesorId(Integer profesorId) {
        this.profesorId = profesorId;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public LocalDateTime getCreacion() {
        return creacion;
    }

    public void setCreacion(LocalDateTime creacion) {
        this.creacion = creacion;
    }

    @Override
    public String toString() {
        return "Profesor{" + "profesorId=" + profesorId + ", alumno=" + alumno + ", creacion=" + creacion + '}';
    }

}
