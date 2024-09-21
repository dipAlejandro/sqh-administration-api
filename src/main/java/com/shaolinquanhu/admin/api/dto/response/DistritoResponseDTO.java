package com.shaolinquanhu.admin.api.dto.response;

import java.time.LocalDateTime;

/**
 *
 * @author dahl
 */
public class DistritoResponseDTO {

    private Integer distritoId;
    private String nombre;
    private String direccion;
    private LocalDateTime creacion;

    public DistritoResponseDTO() {
    }

    public DistritoResponseDTO(Integer distritoId, String nombre, String direccion, LocalDateTime creacion) {
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
        return "DistritoResponseDTO{" + "distritoId=" + distritoId + ", nombre=" + nombre + ", direccion=" + direccion + ", creacion=" + creacion + '}';
    }

}
