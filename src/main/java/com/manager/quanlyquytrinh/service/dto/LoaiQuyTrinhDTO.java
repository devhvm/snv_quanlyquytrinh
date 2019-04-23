package com.manager.quanlyquytrinh.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the LoaiQuyTrinh entity.
 */
public class LoaiQuyTrinhDTO extends AbstractAuditingDTO implements Serializable {

    private Long id;

    @NotNull
    private String loaiQuyTrinhCode;

    private String methodName;

    private String entityName;

    private String serviceName;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLoaiQuyTrinhCode() {
        return loaiQuyTrinhCode;
    }

    public void setLoaiQuyTrinhCode(String loaiQuyTrinhCode) {
        this.loaiQuyTrinhCode = loaiQuyTrinhCode;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        LoaiQuyTrinhDTO loaiQuyTrinhDTO = (LoaiQuyTrinhDTO) o;
        if (loaiQuyTrinhDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), loaiQuyTrinhDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "LoaiQuyTrinhDTO{" +
            "id=" + getId() +
            ", loaiQuyTrinhCode='" + getLoaiQuyTrinhCode() + "'" +
            ", methodName='" + getMethodName() + "'" +
            ", entityName='" + getEntityName() + "'" +
            ", serviceName='" + getServiceName() + "'" +
            "}";
    }
}
