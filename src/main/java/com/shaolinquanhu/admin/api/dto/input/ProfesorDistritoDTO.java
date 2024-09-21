package com.shaolinquanhu.admin.api.dto.input;

/**
 *
 * @author dahl
 */
public class ProfesorDistritoDTO {

    private Integer distritoId;
    private Integer profesorId;

    public ProfesorDistritoDTO() {
    }

    public ProfesorDistritoDTO(Integer distritoId, Integer profesorId) {
        this.distritoId = distritoId;
        this.profesorId = profesorId;
    }

    public Integer getDistritoId() {
        return distritoId;
    }

    public void setDistritoId(Integer distritoId) {
        this.distritoId = distritoId;
    }

    public Integer getProfesorId() {
        return profesorId;
    }

    public void setProfesorId(Integer profesorId) {
        this.profesorId = profesorId;
    }

    @Override
    public String toString() {
        return "ProfesorDistritoDTO{" + "distritoId=" + distritoId + ", profesorId=" + profesorId + '}';
    }

}
