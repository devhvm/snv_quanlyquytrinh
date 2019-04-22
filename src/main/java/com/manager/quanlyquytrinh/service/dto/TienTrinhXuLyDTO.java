package com.manager.quanlyquytrinh.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the TienTrinhXuLy entity.
 */
public class TienTrinhXuLyDTO implements Serializable {

    private Long id;

    @NotNull
    private String batdauCode;

    @NotNull
    private String ketThucCode;


    private Long tienTrinhId;

    private String tienTrinhName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBatdauCode() {
        return batdauCode;
    }

    public void setBatdauCode(String batdauCode) {
        this.batdauCode = batdauCode;
    }

    public String getKetThucCode() {
        return ketThucCode;
    }

    public void setKetThucCode(String ketThucCode) {
        this.ketThucCode = ketThucCode;
    }

    public Long getTienTrinhId() {
        return tienTrinhId;
    }

    public void setTienTrinhId(Long tienTrinhId) {
        this.tienTrinhId = tienTrinhId;
    }

    public String getTienTrinhName() {
        return tienTrinhName;
    }

    public void setTienTrinhName(String tienTrinhName) {
        this.tienTrinhName = tienTrinhName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TienTrinhXuLyDTO tienTrinhXuLyDTO = (TienTrinhXuLyDTO) o;
        if (tienTrinhXuLyDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), tienTrinhXuLyDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TienTrinhXuLyDTO{" +
            "id=" + getId() +
            ", batdauCode='" + getBatdauCode() + "'" +
            ", ketThucCode='" + getKetThucCode() + "'" +
            ", tienTrinh=" + getTienTrinhId() +
            ", tienTrinh='" + getTienTrinhName() + "'" +
            "}";
    }
}
