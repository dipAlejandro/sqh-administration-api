package com.shaolinquanhu.admin.api.dto.response;

/**
 *
 * @author dahl
 */
public class ProfesorDistritoResponseDTO {

    private Integer profesorId;
    private Integer distritoId;
    private String nombreDistrito;
    private String nombreProfesor;

    public ProfesorDistritoResponseDTO() {
    }

    public ProfesorDistritoResponseDTO(Integer profesorId, Integer distritoId, String nombreDistrito, String nombreProfesor) {
        this.profesorId = profesorId;
        this.distritoId = distritoId;
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

    public Integer getProfesorId() {
        return profesorId;
    }

    public void setProfesorId(Integer profesorId) {
        this.profesorId = profesorId;
    }

    public Integer getDistritoId() {
        return distritoId;
    }

    public void setDistritoId(Integer distritoId) {
        this.distritoId = distritoId;
    }

    @Override
    public String toString() {
        return "ProfesorDistritoResponseDTO{" + "profesorId=" + profesorId + ", distritoId=" + distritoId + ", nombreDistrito=" + nombreDistrito + ", nombreProfesor=" + nombreProfesor + '}';
    }

}
