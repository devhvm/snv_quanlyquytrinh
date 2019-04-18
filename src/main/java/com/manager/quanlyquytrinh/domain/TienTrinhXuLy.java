package com.manager.quanlyquytrinh.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A TienTrinhXuLy.
 */
@Entity
@Table(name = "tien_trinh_xu_ly")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class TienTrinhXuLy extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "batdau_code", nullable = false)
    private String batdauCode;

    @NotNull
    @Column(name = "ket_thuc_code", nullable = false)
    private String ketThucCode;

    @ManyToOne
    @JsonIgnoreProperties("tienTrinhXuLies")
    private TienTrinh tienTrinh;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBatdauCode() {
        return batdauCode;
    }

    public TienTrinhXuLy batdauCode(String batdauCode) {
        this.batdauCode = batdauCode;
        return this;
    }

    public void setBatdauCode(String batdauCode) {
        this.batdauCode = batdauCode;
    }

    public String getKetThucCode() {
        return ketThucCode;
    }

    public TienTrinhXuLy ketThucCode(String ketThucCode) {
        this.ketThucCode = ketThucCode;
        return this;
    }

    public void setKetThucCode(String ketThucCode) {
        this.ketThucCode = ketThucCode;
    }

    public TienTrinh getTienTrinh() {
        return tienTrinh;
    }

    public TienTrinhXuLy tienTrinh(TienTrinh tienTrinh) {
        this.tienTrinh = tienTrinh;
        return this;
    }

    public void setTienTrinh(TienTrinh tienTrinh) {
        this.tienTrinh = tienTrinh;
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
        TienTrinhXuLy tienTrinhXuLy = (TienTrinhXuLy) o;
        if (tienTrinhXuLy.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), tienTrinhXuLy.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TienTrinhXuLy{" +
            "id=" + getId() +
            ", batdauCode='" + getBatdauCode() + "'" +
            ", ketThucCode='" + getKetThucCode() + "'" +
            "}";
    }
}
