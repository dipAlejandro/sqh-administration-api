package com.shaolinquanhu.admin.api.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;

/**
 *
 * @author dahl
 */
public class AlumnoResponseDTO {

    private String nombres;
    private String apellidos;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate fechaNacimiento;
    private String dni;
    private String telefono;
    private String direccion;
    private String categoria;
    private String comentarios;
    private String nombreDistrito;

    public AlumnoResponseDTO() {
    }

    public AlumnoResponseDTO(String nombres, String apellidos, LocalDate fechaNacimiento, String dni, String telefono, String direccion, String categoria, String comentarios, String nombreDistrito) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.dni = dni;
        this.telefono = telefono;
        this.direccion = direccion;
        this.categoria = categoria;
        this.comentarios = comentarios;
        this.nombreDistrito = nombreDistrito;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public String getNombreDistrito() {
        return nombreDistrito;
    }

    public void setNombreDistrito(String nombreDistrito) {
        this.nombreDistrito = nombreDistrito;
    }

    @Override
    public String toString() {
        return "AlumnoCreateDTO{" + "nombres=" + nombres + ", apellidos=" + apellidos + ", fechaNacimiento=" + fechaNacimiento + ", dni=" + dni + ", telefono=" + telefono + ", direccion=" + direccion + ", nombreDistrito=" + nombreDistrito + '}';
    }

}
