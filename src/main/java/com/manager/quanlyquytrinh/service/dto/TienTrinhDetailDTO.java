package com.manager.quanlyquytrinh.service.dto;

import com.manager.quanlyquytrinh.domain.AbstractAuditingEntity;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * A DTO for the TienTrinh entity.
 */
public class TienTrinhDetailDTO implements Serializable {

    private TienTrinhDTO tienTrinhBatDau;

    private List<TienTrinhDTO> tienTrinhKetThucs;

    public TienTrinhDTO getTienTrinhBatDau() {
        return tienTrinhBatDau;
    }

    public void setTienTrinhBatDau(TienTrinhDTO tienTrinhBatDau) {
        this.tienTrinhBatDau = tienTrinhBatDau;
    }

    public List<TienTrinhDTO> getTienTrinhKetThucs() {
        return tienTrinhKetThucs;
    }

    public void setTienTrinhKetThucs(List<TienTrinhDTO> tienTrinhKetThucs) {
        this.tienTrinhKetThucs = tienTrinhKetThucs;
    }
}
