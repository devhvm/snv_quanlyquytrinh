package com.manager.quanlyquytrinh.service.mapper;

import com.manager.quanlyquytrinh.domain.*;
import com.manager.quanlyquytrinh.service.dto.TienTrinhDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity TienTrinh and its DTO TienTrinhDTO.
 */
@Mapper(componentModel = "spring", uses = {QuyTrinhMapper.class})
public interface TienTrinhMapper extends EntityMapper<TienTrinhDTO, TienTrinh> {

    @Mapping(source = "quyTrinh.id", target = "quyTrinhId")
    @Mapping(source = "quyTrinh.name", target = "quyTrinhName")
    TienTrinhDTO toDto(TienTrinh tienTrinh);

    @Mapping(target = "tienTrinhXuLies", ignore = true)
    @Mapping(source = "quyTrinhId", target = "quyTrinh")
    TienTrinh toEntity(TienTrinhDTO tienTrinhDTO);

    default TienTrinh fromId(Long id) {
        if (id == null) {
            return null;
        }
        TienTrinh tienTrinh = new TienTrinh();
        tienTrinh.setId(id);
        return tienTrinh;
    }
}
