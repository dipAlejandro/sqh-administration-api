package com.shaolinquanhu.admin.api.dto.response;

/**
 *
 * @author dahl
 */
public class ProfesorResponseDTO {

    private Integer profesorId;
    private String nombreProfesor;

    public ProfesorResponseDTO() {
    }

    public ProfesorResponseDTO(Integer profesorId, String nombreProfesor) {
        this.profesorId = profesorId;
        this.nombreProfesor = this.nombreProfesor;
    }

    public Integer getProfesorId() {
        return profesorId;
    }

    public void setProfesorId(Integer profesorId) {
        this.profesorId = profesorId;
    }

    public String getNombreProfesor() {
        return nombreProfesor;
    }

    public void setNombreProfesor(String nombreProfesor) {
        this.nombreProfesor = nombreProfesor;
    }

    @Override
    public String toString() {
        return "ProfesorResponseDTO{" + "profesorId=" + profesorId + ", nombreProfesor=" + nombreProfesor + '}';
    }

}
