package com.shaolinquanhu.admin.api.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 *
 * @author Dahl
 */
@Entity
@Table(name = "profesor_distrito")
public class ProfesorDistrito {

    @EmbeddedId
    private ProfesorDistritoId id;

    @ManyToOne
    @MapsId("profesorId")
    @JoinColumn(name = "profesor_id")
    private Profesor profesor;
    @ManyToOne
    @MapsId("distritoId")
    @JoinColumn(name = "distrito_id")
    private Distrito distrito;
    @Column(nullable = false)
    private LocalDateTime creacion;

    public ProfesorDistrito() {
    }

    public ProfesorDistrito(ProfesorDistritoId id, Profesor profesor, Distrito distrito, LocalDateTime creacion) {
        this.id = id;
        this.profesor = profesor;
        this.distrito = distrito;
        this.creacion = creacion;
    }

    public ProfesorDistritoId getId() {
        return id;
    }

    public void setId(ProfesorDistritoId id) {
        this.id = id;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public Distrito getDistrito() {
        return distrito;
    }

    public void setDistrito(Distrito distrito) {
        this.distrito = distrito;
    }

    public LocalDateTime getCreacion() {
        return creacion;
    }

    public void setCreacion(LocalDateTime creacion) {
        this.creacion = creacion;
    }

    @Override
    public String toString() {
        return "ProfesorDistrito{" + "id=" + id + ", profesor=" + profesor + ", distrito=" + distrito + ", creacion=" + creacion + '}';
    }

}
