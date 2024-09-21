package com.shaolinquanhu.admin.api.dto.input;

import java.time.LocalDateTime;

/**
 *
 * @author dahl
 */
public class DistritoDTO {

    private String nombre;
    private String direccion;
    private LocalDateTime creacion;

    public DistritoDTO() {
    }

    public DistritoDTO(String nombre, String direccion, LocalDateTime creacion) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.creacion = creacion;
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
        return "DistritoDTO{ nombre=" + nombre + ", direccion=" + direccion + ", creacion=" + creacion + '}';
    }

}
