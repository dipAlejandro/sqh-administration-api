package com.shaolinquanhu.admin.api.dto.input;

import java.time.LocalDateTime;

/**
 *
 * @author dahl
 */
public class UsuarioDTO {

    private Integer profesorId;
    private String nombreUsuario;
    private String email;
    private String contrasenia;
    private String rol = "USER";
    private LocalDateTime creacion;

    public UsuarioDTO() {
    }

    public UsuarioDTO(Integer profesorId, String nombreUsuario, String email, String contrasenia, LocalDateTime creacion) {
        this.profesorId = profesorId;
        this.nombreUsuario = nombreUsuario;
        this.email = email;
        this.contrasenia = contrasenia;
        this.creacion = creacion;
    }

    public Integer getProfesorId() {
        return profesorId;
    }

    public void setProfesorId(Integer profesorId) {
        this.profesorId = profesorId;
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

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
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
        return "UsuarioCreateDTO{ profesorId=" + profesorId + ", nombreUsuario=" + nombreUsuario + ", email=" + email + ", contrasenia=" + contrasenia + ", rol=" + rol + ", creacion=" + creacion + '}';
    }

}
