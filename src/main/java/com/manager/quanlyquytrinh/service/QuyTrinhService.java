package com.manager.quanlyquytrinh.service;

import com.manager.quanlyquytrinh.domain.QuyTrinh;
import com.manager.quanlyquytrinh.repository.QuyTrinhRepository;
import com.manager.quanlyquytrinh.service.dto.QuyTrinhDTO;
import com.manager.quanlyquytrinh.service.dto.QuyTrinhDetailDTO;
import com.manager.quanlyquytrinh.service.mapper.QuyTrinhDetailMapper;
import com.manager.quanlyquytrinh.service.mapper.QuyTrinhMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing QuyTrinh.
 */
@Service
@Transactional
public class QuyTrinhService {

    private final Logger log = LoggerFactory.getLogger(QuyTrinhService.class);

    private final QuyTrinhRepository quyTrinhRepository;

    private final QuyTrinhMapper quyTrinhMapper;

    private final QuyTrinhDetailMapper quyTrinhDetailMapper;

    public QuyTrinhService(QuyTrinhRepository quyTrinhRepository, QuyTrinhMapper quyTrinhMapper, QuyTrinhDetailMapper quyTrinhDetailMapper) {
        this.quyTrinhRepository = quyTrinhRepository;
        this.quyTrinhMapper = quyTrinhMapper;
        this.quyTrinhDetailMapper = quyTrinhDetailMapper;
    }

    /**
     * Save a quyTrinh.
     *
     * @param quyTrinhDTO the entity to save
     * @return the persisted entity
     */
    public QuyTrinhDTO save(QuyTrinhDTO quyTrinhDTO) {
        log.debug("Request to save QuyTrinh : {}", quyTrinhDTO);
        QuyTrinh quyTrinh = quyTrinhMapper.toEntity(quyTrinhDTO);
        quyTrinh = quyTrinhRepository.save(quyTrinh);
        return quyTrinhMapper.toDto(quyTrinh);
    }

    /**
     * Get all the quyTrinhs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<QuyTrinhDTO> findAll(Pageable pageable) {
        log.debug("Request to get all QuyTrinhs");
        return quyTrinhRepository.findAll(pageable)
            .map(quyTrinhMapper::toDto);
    }


    /**
     * Get one quyTrinh by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<QuyTrinhDTO> findOne(Long id) {
        log.debug("Request to get QuyTrinh : {}", id);
        return quyTrinhRepository.findById(id)
            .map(quyTrinhMapper::toDto);
    }

    /**
     * Delete the quyTrinh by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete QuyTrinh : {}", id);
        quyTrinhRepository.deleteById(id);
    }

    /**
     * Get quyTrinh detail by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<QuyTrinhDetailDTO> findDetail(Long id) {
        log.debug("Request to get QuyTrinh : {}", id);
        Optional<QuyTrinh> quiTrinh = quyTrinhRepository.findById(id);
        return quiTrinh
            .map(quyTrinhDetailMapper::toDto);
    }
}
