package com.manager.quanlyquytrinh.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A QuyTrinh.
 */
@Entity
@Table(name = "quy_trinh")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class QuyTrinh implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "quy_trinh_code", nullable = false)
    private String quyTrinhCode;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuyTrinhCode() {
        return quyTrinhCode;
    }

    public QuyTrinh quyTrinhCode(String quyTrinhCode) {
        this.quyTrinhCode = quyTrinhCode;
        return this;
    }

    public void setQuyTrinhCode(String quyTrinhCode) {
        this.quyTrinhCode = quyTrinhCode;
    }

    public String getName() {
        return name;
    }

    public QuyTrinh name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
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
        QuyTrinh quyTrinh = (QuyTrinh) o;
        if (quyTrinh.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), quyTrinh.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "QuyTrinh{" +
            "id=" + getId() +
            ", quyTrinhCode='" + getQuyTrinhCode() + "'" +
            ", name='" + getName() + "'" +
            "}";
    }
}
