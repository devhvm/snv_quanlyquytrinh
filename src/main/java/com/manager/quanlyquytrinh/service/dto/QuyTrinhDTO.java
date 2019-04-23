package com.manager.quanlyquytrinh.service.dto;
import com.manager.quanlyquytrinh.domain.AbstractAuditingEntity;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the QuyTrinh entity.
 */
public class QuyTrinhDTO extends AbstractAuditingEntity implements Serializable {

    private Long id;

    @NotNull
    private String quyTrinhCode;

    @NotNull
    private String name;


    private Long loaiQuyTrinhId;

    private String loaiQuyTrinhName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuyTrinhCode() {
        return quyTrinhCode;
    }

    public void setQuyTrinhCode(String quyTrinhCode) {
        this.quyTrinhCode = quyTrinhCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getLoaiQuyTrinhId() {
        return loaiQuyTrinhId;
    }

    public void setLoaiQuyTrinhId(Long loaiQuyTrinhId) {
        this.loaiQuyTrinhId = loaiQuyTrinhId;
    }

    public String getLoaiQuyTrinhName() {
        return loaiQuyTrinhName;
    }

    public void setLoaiQuyTrinhName(String loaiQuyTrinhName) {
        this.loaiQuyTrinhName = loaiQuyTrinhName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        QuyTrinhDTO quyTrinhDTO = (QuyTrinhDTO) o;
        if (quyTrinhDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), quyTrinhDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "QuyTrinhDTO{" +
            "id=" + getId() +
            ", quyTrinhCode='" + getQuyTrinhCode() + "'" +
            ", name='" + getName() + "'" +
            ", loaiQuyTrinh=" + getLoaiQuyTrinhId() +
            ", loaiQuyTrinh='" + getLoaiQuyTrinhName() + "'" +
            "}";
    }
}
