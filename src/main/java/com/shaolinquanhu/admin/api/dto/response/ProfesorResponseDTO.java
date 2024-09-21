package com.shaolinquanhu.admin.api.dto.response;

/**
 *
 * @author dahl
 */
public class ProfesorResponseDTO {

    private Integer profesorId;
    private String nombreCompletoProfesor;

    public ProfesorResponseDTO() {
    }

    public ProfesorResponseDTO(Integer profesorId, String nombreCompletoProfesor) {
        this.profesorId = profesorId;
        this.nombreCompletoProfesor = nombreCompletoProfesor;
    }

    public Integer getProfesorId() {
        return profesorId;
    }

    public void setProfesorId(Integer profesorId) {
        this.profesorId = profesorId;
    }

    public String getNombreCompletoProfesor() {
        return nombreCompletoProfesor;
    }

    public void setNombreCompletoProfesor(String nombreCompletoProfesor) {
        this.nombreCompletoProfesor = nombreCompletoProfesor;
    }

    @Override
    public String toString() {
        return "ProfesorResponseDTO{" + "profesorId=" + profesorId + ", nombreCompletoProfesor=" + nombreCompletoProfesor + '}';
    }

}
