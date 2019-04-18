package com.manager.quanlyquytrinh.service.mapper;

import com.manager.quanlyquytrinh.domain.*;
import com.manager.quanlyquytrinh.service.dto.QuyTrinhDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity QuyTrinh and its DTO QuyTrinhDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface QuyTrinhMapper extends EntityMapper<QuyTrinhDTO, QuyTrinh> {


    @Mapping(target = "tienTrinhs", ignore = true)
    QuyTrinh toEntity(QuyTrinhDTO quyTrinhDTO);

    default QuyTrinh fromId(Long id) {
        if (id == null) {
            return null;
        }
        QuyTrinh quyTrinh = new QuyTrinh();
        quyTrinh.setId(id);
        return quyTrinh;
    }
}
