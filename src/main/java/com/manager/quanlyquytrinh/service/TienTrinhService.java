package com.manager.quanlyquytrinh.service;

import com.manager.quanlyquytrinh.domain.TienTrinh;
import com.manager.quanlyquytrinh.repository.TienTrinhRepository;
import com.manager.quanlyquytrinh.service.dto.TienTrinhDTO;
import com.manager.quanlyquytrinh.service.dto.TienTrinhDetailDTO;
import com.manager.quanlyquytrinh.service.mapper.TienTrinhDetailMapper;
import com.manager.quanlyquytrinh.service.mapper.TienTrinhMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing TienTrinh.
 */
@Service
@Transactional
public class TienTrinhService {

    private final Logger log = LoggerFactory.getLogger(TienTrinhService.class);

    private final TienTrinhRepository tienTrinhRepository;

    private final TienTrinhMapper tienTrinhMapper;

    private final TienTrinhDetailMapper tienTrinhDetailMapper;

    public TienTrinhService(TienTrinhRepository tienTrinhRepository, TienTrinhMapper tienTrinhMapper, TienTrinhDetailMapper tienTrinhDetailMapper) {
        this.tienTrinhRepository = tienTrinhRepository;
        this.tienTrinhMapper = tienTrinhMapper;
        this.tienTrinhDetailMapper = tienTrinhDetailMapper;
    }

    /**
     * Save a tienTrinh.
     *
     * @param tienTrinhDTO the entity to save
     * @return the persisted entity
     */
    public TienTrinhDTO save(TienTrinhDTO tienTrinhDTO) {
        log.debug("Request to save TienTrinh : {}", tienTrinhDTO);
        TienTrinh tienTrinh = tienTrinhMapper.toEntity(tienTrinhDTO);
        tienTrinh = tienTrinhRepository.save(tienTrinh);
        return tienTrinhMapper.toDto(tienTrinh);
    }

    /**
     * Get all the tienTrinhs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<TienTrinhDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TienTrinhs");
        return tienTrinhRepository.findAll(pageable)
            .map(tienTrinhMapper::toDto);
    }


    /**
     * Get one tienTrinh by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<TienTrinhDTO> findOne(Long id) {
        log.debug("Request to get TienTrinh : {}", id);
        return tienTrinhRepository.findById(id)
            .map(tienTrinhMapper::toDto);
    }

    /**
     * Delete the tienTrinh by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete TienTrinh : {}", id);
        tienTrinhRepository.deleteById(id);
    }
}
