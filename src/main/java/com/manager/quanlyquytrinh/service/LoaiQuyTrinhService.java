package com.manager.quanlyquytrinh.service;

import com.manager.quanlyquytrinh.domain.LoaiQuyTrinh;
import com.manager.quanlyquytrinh.repository.LoaiQuyTrinhRepository;
import com.manager.quanlyquytrinh.service.dto.LoaiQuyTrinhDTO;
import com.manager.quanlyquytrinh.service.mapper.LoaiQuyTrinhMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing LoaiQuyTrinh.
 */
@Service
@Transactional
public class LoaiQuyTrinhService {

    private final Logger log = LoggerFactory.getLogger(LoaiQuyTrinhService.class);

    private final LoaiQuyTrinhRepository loaiQuyTrinhRepository;

    private final LoaiQuyTrinhMapper loaiQuyTrinhMapper;

    public LoaiQuyTrinhService(LoaiQuyTrinhRepository loaiQuyTrinhRepository, LoaiQuyTrinhMapper loaiQuyTrinhMapper) {
        this.loaiQuyTrinhRepository = loaiQuyTrinhRepository;
        this.loaiQuyTrinhMapper = loaiQuyTrinhMapper;
    }

    /**
     * Save a loaiQuyTrinh.
     *
     * @param loaiQuyTrinhDTO the entity to save
     * @return the persisted entity
     */
    public LoaiQuyTrinhDTO save(LoaiQuyTrinhDTO loaiQuyTrinhDTO) {
        log.debug("Request to save LoaiQuyTrinh : {}", loaiQuyTrinhDTO);
        LoaiQuyTrinh loaiQuyTrinh = loaiQuyTrinhMapper.toEntity(loaiQuyTrinhDTO);
        loaiQuyTrinh = loaiQuyTrinhRepository.save(loaiQuyTrinh);
        return loaiQuyTrinhMapper.toDto(loaiQuyTrinh);
    }

    /**
     * Get all the loaiQuyTrinhs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<LoaiQuyTrinhDTO> findAll(Pageable pageable) {
        log.debug("Request to get all LoaiQuyTrinhs");
        return loaiQuyTrinhRepository.findAll(pageable)
            .map(loaiQuyTrinhMapper::toDto);
    }


    /**
     * Get one loaiQuyTrinh by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<LoaiQuyTrinhDTO> findOne(Long id) {
        log.debug("Request to get LoaiQuyTrinh : {}", id);
        return loaiQuyTrinhRepository.findById(id)
            .map(loaiQuyTrinhMapper::toDto);
    }

    /**
     * Delete the loaiQuyTrinh by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete LoaiQuyTrinh : {}", id);
        loaiQuyTrinhRepository.deleteById(id);
    }
}
