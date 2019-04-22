package com.manager.quanlyquytrinh.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A TienTrinh.
 */
@Entity
@Table(name = "tien_trinh")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class TienTrinh implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "menu_item_code", nullable = false)
    private String menuItemCode;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "screen_code", nullable = false)
    private String screenCode;

    @NotNull
    @Column(name = "status", nullable = false)
    private String status;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMenuItemCode() {
        return menuItemCode;
    }

    public TienTrinh menuItemCode(String menuItemCode) {
        this.menuItemCode = menuItemCode;
        return this;
    }

    public void setMenuItemCode(String menuItemCode) {
        this.menuItemCode = menuItemCode;
    }

    public String getName() {
        return name;
    }

    public TienTrinh name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScreenCode() {
        return screenCode;
    }

    public TienTrinh screenCode(String screenCode) {
        this.screenCode = screenCode;
        return this;
    }

    public void setScreenCode(String screenCode) {
        this.screenCode = screenCode;
    }

    public String getStatus() {
        return status;
    }

    public TienTrinh status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TienTrinh tienTrinh = (TienTrinh) o;
        if (tienTrinh.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), tienTrinh.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TienTrinh{" +
            "id=" + getId() +
            ", menuItemCode='" + getMenuItemCode() + "'" +
            ", name='" + getName() + "'" +
            ", screenCode='" + getScreenCode() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
