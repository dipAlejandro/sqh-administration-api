package com.shaolinquanhu.admin.api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 *
 * @author Dahl
 */
@Entity
@Table(name = "distrito")
public class Distrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "distrito_id", nullable = false)
    private Integer distritoId;
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private String direccion;
    @Column(nullable = false)
    private LocalDateTime creacion;

    public Distrito() {

    }

    public Distrito(Integer distritoId, String nombre, String direccion, LocalDateTime creacion) {
        this.distritoId = distritoId;
        this.nombre = nombre;
        this.direccion = direccion;
        this.creacion = creacion;
    }

    public Integer getDistritoId() {
        return distritoId;
    }

    public void setDistritoId(Integer distritoId) {
        this.distritoId = distritoId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public LocalDateTime getCreacion() {
        return creacion;
    }

    public void setCreacion(LocalDateTime creacion) {
        this.creacion = creacion;
    }

    @Override
    public String toString() {
        return "Distrito{" + "distritoId=" + distritoId + ", nombre=" + nombre + ", direccion=" + direccion + ", creacion=" + creacion + '}';
    }

}
