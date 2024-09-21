package com.shaolinquanhu.admin.api.dto.response;

/**
 *
 * @author dahl
 */
public class ProfesorDistritoResponseDTO {

    private String nombreDistrito;
    private String nombreProfesor;

    public ProfesorDistritoResponseDTO() {
    }

    public ProfesorDistritoResponseDTO(String nombreDistrito, String nombreProfesor) {
        this.nombreDistrito = nombreDistrito;
        this.nombreProfesor = nombreProfesor;
    }

    public String getNombreDistrito() {
        return nombreDistrito;
    }

    public void setNombreDistrito(String nombreDistrito) {
        this.nombreDistrito = nombreDistrito;
    }

    public String getNombreProfesor() {
        return nombreProfesor;
    }

    public void setNombreProfesor(String nombreProfesor) {
        this.nombreProfesor = nombreProfesor;
    }

    @Override
    public String toString() {
        return "ProfesorDistritoResponseDTO{" + "nombreDistrito=" + nombreDistrito + ", nombreProfesor=" + nombreProfesor + '}';
    }

}
