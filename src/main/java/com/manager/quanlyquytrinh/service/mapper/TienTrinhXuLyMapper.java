package com.manager.quanlyquytrinh.service.mapper;

import com.manager.quanlyquytrinh.domain.*;
import com.manager.quanlyquytrinh.service.dto.TienTrinhXuLyDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity TienTrinhXuLy and its DTO TienTrinhXuLyDTO.
 */
@Mapper(componentModel = "spring", uses = {TienTrinhMapper.class})
public interface TienTrinhXuLyMapper extends EntityMapper<TienTrinhXuLyDTO, TienTrinhXuLy> {

    @Mapping(source = "tienTrinh.id", target = "tienTrinhId")
    @Mapping(source = "tienTrinh.name", target = "tienTrinhName")
    TienTrinhXuLyDTO toDto(TienTrinhXuLy tienTrinhXuLy);

    @Mapping(source = "tienTrinhId", target = "tienTrinh")
    TienTrinhXuLy toEntity(TienTrinhXuLyDTO tienTrinhXuLyDTO);

    default TienTrinhXuLy fromId(Long id) {
        if (id == null) {
            return null;
        }
        TienTrinhXuLy tienTrinhXuLy = new TienTrinhXuLy();
        tienTrinhXuLy.setId(id);
        return tienTrinhXuLy;
    }
}
