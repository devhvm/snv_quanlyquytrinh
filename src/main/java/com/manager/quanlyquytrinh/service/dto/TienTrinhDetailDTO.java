package com.manager.quanlyquytrinh.service.dto;

import com.manager.quanlyquytrinh.domain.AbstractAuditingEntity;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * A DTO for the TienTrinh entity.
 */
public class TienTrinhDetailDTO extends AbstractAuditingEntity implements Serializable {

    private Long id;

    @NotNull
    private String tienTrinhCode;

    @NotNull
    private String name;

    @NotNull
    private String screenCode;

    @NotNull
    private String status;


    private Long quyTrinhId;

    private String quyTrinhName;

    private List<TienTrinhXuLyDTO> tienTrinhXuLies;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScreenCode() {
        return screenCode;
    }

    public void setScreenCode(String screenCode) {
        this.screenCode = screenCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getQuyTrinhId() {
        return quyTrinhId;
    }

    public void setQuyTrinhId(Long quyTrinhId) {
        this.quyTrinhId = quyTrinhId;
    }

    public String getQuyTrinhName() {
        return quyTrinhName;
    }

    public void setQuyTrinhName(String quyTrinhName) {
        this.quyTrinhName = quyTrinhName;
    }

    public String getTienTrinhCode() {
        return tienTrinhCode;
    }

    public void setTienTrinhCode(String tienTrinhCode) {
        this.tienTrinhCode = tienTrinhCode;
    }

    public List<TienTrinhXuLyDTO> getTienTrinhXuLies() {
        return tienTrinhXuLies;
    }

    public void setTienTrinhXuLies(List<TienTrinhXuLyDTO> tienTrinhXuLies) {
        this.tienTrinhXuLies = tienTrinhXuLies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TienTrinhDetailDTO tienTrinhDTO = (TienTrinhDetailDTO) o;
        if (tienTrinhDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), tienTrinhDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TienTrinhDTO{" +
            "id=" + getId() +
            ", tienTrinhCode='" + getTienTrinhCode() + "'" +
            ", name='" + getName() + "'" +
            ", screenCode='" + getScreenCode() + "'" +
            ", status='" + getStatus() + "'" +
            ", quyTrinh=" + getQuyTrinhId() +
            ", quyTrinh='" + getQuyTrinhName() + "'" +
            "}";
    }
}