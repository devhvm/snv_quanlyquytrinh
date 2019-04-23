package com.manager.quanlyquytrinh.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A LoaiQuyTrinh.
 */
@Entity
@Table(name = "loai_quy_trinh")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class LoaiQuyTrinh extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "loai_quy_trinh_code", nullable = false)
    private String loaiQuyTrinhCode;

    @Column(name = "method_name")
    private String methodName;

    @Column(name = "entity_name")
    private String entityName;

    @Column(name = "service_name")
    private String serviceName;

    @OneToMany(mappedBy = "loaiQuyTrinh")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<QuyTrinh> quyTrinhs = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLoaiQuyTrinhCode() {
        return loaiQuyTrinhCode;
    }

    public LoaiQuyTrinh loaiQuyTrinhCode(String loaiQuyTrinhCode) {
        this.loaiQuyTrinhCode = loaiQuyTrinhCode;
        return this;
    }

    public void setLoaiQuyTrinhCode(String loaiQuyTrinhCode) {
        this.loaiQuyTrinhCode = loaiQuyTrinhCode;
    }

    public String getMethodName() {
        return methodName;
    }

    public LoaiQuyTrinh methodName(String methodName) {
        this.methodName = methodName;
        return this;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getEntityName() {
        return entityName;
    }

    public LoaiQuyTrinh entityName(String entityName) {
        this.entityName = entityName;
        return this;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public LoaiQuyTrinh serviceName(String serviceName) {
        this.serviceName = serviceName;
        return this;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Set<QuyTrinh> getQuyTrinhs() {
        return quyTrinhs;
    }

    public LoaiQuyTrinh quyTrinhs(Set<QuyTrinh> quyTrinhs) {
        this.quyTrinhs = quyTrinhs;
        return this;
    }

    public LoaiQuyTrinh addQuyTrinh(QuyTrinh quyTrinh) {
        this.quyTrinhs.add(quyTrinh);
        quyTrinh.setLoaiQuyTrinh(this);
        return this;
    }

    public LoaiQuyTrinh removeQuyTrinh(QuyTrinh quyTrinh) {
        this.quyTrinhs.remove(quyTrinh);
        quyTrinh.setLoaiQuyTrinh(null);
        return this;
    }

    public void setQuyTrinhs(Set<QuyTrinh> quyTrinhs) {
        this.quyTrinhs = quyTrinhs;
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
        LoaiQuyTrinh loaiQuyTrinh = (LoaiQuyTrinh) o;
        if (loaiQuyTrinh.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), loaiQuyTrinh.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "LoaiQuyTrinh{" +
            "id=" + getId() +
            ", loaiQuyTrinhCode='" + getLoaiQuyTrinhCode() + "'" +
            ", methodName='" + getMethodName() + "'" +
            ", entityName='" + getEntityName() + "'" +
            ", serviceName='" + getServiceName() + "'" +
            "}";
    }
}
