package com.manager.quanlyquytrinh.service;

import com.manager.quanlyquytrinh.domain.TienTrinhXuLy;
import com.manager.quanlyquytrinh.repository.TienTrinhXuLyRepository;
import com.manager.quanlyquytrinh.service.dto.TienTrinhXuLyDTO;
import com.manager.quanlyquytrinh.service.mapper.TienTrinhXuLyMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing TienTrinhXuLy.
 */
@Service
@Transactional
public class TienTrinhXuLyService {

    private final Logger log = LoggerFactory.getLogger(TienTrinhXuLyService.class);

    private final TienTrinhXuLyRepository tienTrinhXuLyRepository;

    private final TienTrinhXuLyMapper tienTrinhXuLyMapper;

    public TienTrinhXuLyService(TienTrinhXuLyRepository tienTrinhXuLyRepository, TienTrinhXuLyMapper tienTrinhXuLyMapper) {
        this.tienTrinhXuLyRepository = tienTrinhXuLyRepository;
        this.tienTrinhXuLyMapper = tienTrinhXuLyMapper;
    }

    /**
     * Save a tienTrinhXuLy.
     *
     * @param tienTrinhXuLyDTO the entity to save
     * @return the persisted entity
     */
    public TienTrinhXuLyDTO save(TienTrinhXuLyDTO tienTrinhXuLyDTO) {
        log.debug("Request to save TienTrinhXuLy : {}", tienTrinhXuLyDTO);
        TienTrinhXuLy tienTrinhXuLy = tienTrinhXuLyMapper.toEntity(tienTrinhXuLyDTO);
        tienTrinhXuLy = tienTrinhXuLyRepository.save(tienTrinhXuLy);
        return tienTrinhXuLyMapper.toDto(tienTrinhXuLy);
    }

    /**
     * Get all the tienTrinhXuLies.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<TienTrinhXuLyDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TienTrinhXuLies");
        return tienTrinhXuLyRepository.findAll(pageable)
            .map(tienTrinhXuLyMapper::toDto);
    }


    /**
     * Get one tienTrinhXuLy by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<TienTrinhXuLyDTO> findOne(Long id) {
        log.debug("Request to get TienTrinhXuLy : {}", id);
        return tienTrinhXuLyRepository.findById(id)
            .map(tienTrinhXuLyMapper::toDto);
    }

    /**
     * Delete the tienTrinhXuLy by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete TienTrinhXuLy : {}", id);
        tienTrinhXuLyRepository.deleteById(id);
    }
}
