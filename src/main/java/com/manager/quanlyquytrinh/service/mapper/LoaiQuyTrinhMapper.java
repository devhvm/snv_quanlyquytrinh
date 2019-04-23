package com.manager.quanlyquytrinh.service.mapper;

import com.manager.quanlyquytrinh.domain.*;
import com.manager.quanlyquytrinh.service.dto.LoaiQuyTrinhDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity LoaiQuyTrinh and its DTO LoaiQuyTrinhDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface LoaiQuyTrinhMapper extends EntityMapper<LoaiQuyTrinhDTO, LoaiQuyTrinh> {


    @Mapping(target = "quyTrinhs", ignore = true)
    LoaiQuyTrinh toEntity(LoaiQuyTrinhDTO loaiQuyTrinhDTO);

    default LoaiQuyTrinh fromId(Long id) {
        if (id == null) {
            return null;
        }
        LoaiQuyTrinh loaiQuyTrinh = new LoaiQuyTrinh();
        loaiQuyTrinh.setId(id);
        return loaiQuyTrinh;
    }
}
