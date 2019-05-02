package com.manager.quanlyquytrinh.service.mapper;

import com.manager.quanlyquytrinh.domain.QuyTrinh;
import com.manager.quanlyquytrinh.service.dto.QuyTrinhDTO;
import com.manager.quanlyquytrinh.service.dto.QuyTrinhDetailDTO;
import com.manager.quanlyquytrinh.service.dto.TienTrinhDetailDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity QuyTrinh and its DTO QuyTrinhDTO.
 */
@Mapper(componentModel = "spring", uses = {TienTrinhDetailMapper.class})
public interface QuyTrinhDetailMapper extends EntityMapper<QuyTrinhDetailDTO, QuyTrinh> {

    @Mapping(source = "tienTrinhDetails", target = "tienTrinhDetails")
    QuyTrinhDetailDTO toDto(QuyTrinh quyTrinh);
}
