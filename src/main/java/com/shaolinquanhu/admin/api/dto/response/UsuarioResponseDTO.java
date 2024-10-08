package com.shaolinquanhu.admin.api.dto.response;

import java.time.LocalDateTime;

/**
 *
 * @author dahl
 */
public class UsuarioResponseDTO {

    private Integer usuarioId;
    private String nombreProfesor;
    private String nombreUsuario;
    private String email;
    private String rol = "USER";
    private LocalDateTime creacion;

    public UsuarioResponseDTO() {
    }

    public UsuarioResponseDTO(Integer usuarioId, String nombreProfesor, String nombreUsuario, String email, LocalDateTime creacion) {
        this.usuarioId = usuarioId;
        this.nombreProfesor = nombreProfesor;
        this.nombreUsuario = nombreUsuario;
        this.email = email;
        this.creacion = creacion;
    }

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getNombreProfesor() {
        return nombreProfesor;
    }

    public void setNombreProfesor(String nombreProfesor) {
        this.nombreProfesor = nombreProfesor;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public LocalDateTime getCreacion() {
        return creacion;
    }

    public void setCreacion(LocalDateTime creacion) {
        this.creacion = creacion;
    }

    @Override
    public String toString() {
        return "UsuarioResponseDTO{ usuarioId=" + usuarioId + ", nombreProfesor=" + nombreProfesor + ", nombreUsuario=" + nombreUsuario + ", email=" + email + ", rol=" + rol + ", creacion=" + creacion + '}';
    }

}
