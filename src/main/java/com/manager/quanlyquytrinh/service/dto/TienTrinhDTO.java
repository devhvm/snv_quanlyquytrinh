package com.manager.quanlyquytrinh.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the TienTrinh entity.
 */
public class TienTrinhDTO extends AbstractAuditingDTO implements Serializable {

    private Long id;

    @NotNull
    private String menuItemCode;

    @NotNull
    private String name;

    @NotNull
    private String icon;


    private Long quyTrinhId;

    private String quyTrinhName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMenuItemCode() {
        return menuItemCode;
    }

    public void setMenuItemCode(String menuItemCode) {
        this.menuItemCode = menuItemCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TienTrinhDTO tienTrinhDTO = (TienTrinhDTO) o;
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
            ", menuItemCode='" + getMenuItemCode() + "'" +
            ", name='" + getName() + "'" +
            ", icon='" + getIcon() + "'" +
            ", quyTrinh=" + getQuyTrinhId() +
            ", quyTrinh='" + getQuyTrinhName() + "'" +
            "}";
    }
}
