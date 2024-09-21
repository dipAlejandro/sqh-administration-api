package com.shaolinquanhu.admin.api.dto.input;

/**
 *
 * @author dahl
 */
public class ProfesorDTO {

    private Integer alumnoId;

    public ProfesorDTO() {
    }

    public ProfesorDTO(Integer alumnoId) {
        this.alumnoId = alumnoId;
    }

    public Integer getAlumnoId() {
        return alumnoId;
    }

    public void setAlumnoId(Integer alumnoId) {
        this.alumnoId = alumnoId;
    }

    @Override
    public String toString() {
        return "ProfesorDTO{alumnoId=" + alumnoId + '}';
    }

}
