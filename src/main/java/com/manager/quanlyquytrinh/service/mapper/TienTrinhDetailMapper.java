package com.manager.quanlyquytrinh.service.mapper;

import com.manager.quanlyquytrinh.domain.TienTrinh;
import com.manager.quanlyquytrinh.service.dto.TienTrinhDetailDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity TienTrinh and its DTO TienTrinhDTO.
 */
@Mapper(componentModel = "spring")
public interface TienTrinhDetailMapper extends EntityMapper<TienTrinhDetailDTO, TienTrinh> {

    @Mapping(target = "tienTrinhXuLies")
    TienTrinhDetailDTO toDto(TienTrinh tienTrinh);

}
