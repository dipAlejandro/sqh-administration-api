package com.shaolinquanhu.admin.api.entity;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Dahl
 */
@Embeddable
public class ProfesorDistritoId implements Serializable {

    private Integer profesorId;
    private Integer distritoId;

    public ProfesorDistritoId() {
    }

    public ProfesorDistritoId(Integer profesorId, Integer distritoId) {
        this.profesorId = profesorId;
        this.distritoId = distritoId;
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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ProfesorDistritoId that = (ProfesorDistritoId) o;
        return Objects.equals(profesorId, that.profesorId) && Objects.equals(distritoId, that.distritoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(profesorId, distritoId);
    }
}
