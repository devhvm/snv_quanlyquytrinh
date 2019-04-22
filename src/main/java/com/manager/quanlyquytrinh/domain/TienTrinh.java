package com.manager.quanlyquytrinh.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A TienTrinh.
 */
@Entity
@Table(name = "tien_trinh")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class TienTrinh extends AbstractAuditingEntity implements Serializable {

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
    @Column(name = "icon", nullable = false)
    private String icon;

    @OneToMany(mappedBy = "tienTrinh")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<TienTrinhXuLy> tienTrinhXuLies = new HashSet<>();
    @ManyToOne
    @JsonIgnoreProperties("tienTrinhs")
    private QuyTrinh quyTrinh;

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

    public String getIcon() {
        return icon;
    }

    public TienTrinh icon(String icon) {
        this.icon = icon;
        return this;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Set<TienTrinhXuLy> getTienTrinhXuLies() {
        return tienTrinhXuLies;
    }

    public TienTrinh tienTrinhXuLies(Set<TienTrinhXuLy> tienTrinhXuLies) {
        this.tienTrinhXuLies = tienTrinhXuLies;
        return this;
    }

    public TienTrinh addTienTrinhXuLy(TienTrinhXuLy tienTrinhXuLy) {
        this.tienTrinhXuLies.add(tienTrinhXuLy);
        tienTrinhXuLy.setTienTrinh(this);
        return this;
    }

    public TienTrinh removeTienTrinhXuLy(TienTrinhXuLy tienTrinhXuLy) {
        this.tienTrinhXuLies.remove(tienTrinhXuLy);
        tienTrinhXuLy.setTienTrinh(null);
        return this;
    }

    public void setTienTrinhXuLies(Set<TienTrinhXuLy> tienTrinhXuLies) {
        this.tienTrinhXuLies = tienTrinhXuLies;
    }

    public QuyTrinh getQuyTrinh() {
        return quyTrinh;
    }

    public TienTrinh quyTrinh(QuyTrinh quyTrinh) {
        this.quyTrinh = quyTrinh;
        return this;
    }

    public void setQuyTrinh(QuyTrinh quyTrinh) {
        this.quyTrinh = quyTrinh;
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
            ", icon='" + getIcon() + "'" +
            "}";
    }
}
