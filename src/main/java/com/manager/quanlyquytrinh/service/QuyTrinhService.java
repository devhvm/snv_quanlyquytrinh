package com.manager.quanlyquytrinh.service;

import com.manager.quanlyquytrinh.domain.QuyTrinh;
import com.manager.quanlyquytrinh.domain.TienTrinh;
import com.manager.quanlyquytrinh.domain.TienTrinhXuLy;
import com.manager.quanlyquytrinh.repository.QuyTrinhRepository;
import com.manager.quanlyquytrinh.service.dto.QuyTrinhDTO;
import com.manager.quanlyquytrinh.service.dto.QuyTrinhDetailDTO;
import com.manager.quanlyquytrinh.service.dto.TienTrinhDTO;
import com.manager.quanlyquytrinh.service.dto.TienTrinhDetailDTO;
import com.manager.quanlyquytrinh.service.mapper.QuyTrinhMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Service Implementation for managing QuyTrinh.
 */
@Service
@Transactional
public class QuyTrinhService {

    private final Logger log = LoggerFactory.getLogger(QuyTrinhService.class);

    private final QuyTrinhRepository quyTrinhRepository;

    private final QuyTrinhMapper quyTrinhMapper;

    public QuyTrinhService(QuyTrinhRepository quyTrinhRepository, QuyTrinhMapper quyTrinhMapper) {
        this.quyTrinhRepository = quyTrinhRepository;
        this.quyTrinhMapper = quyTrinhMapper;
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
     * Get quyTrinh detail by quyTrinhCode.
     *
     * @param quyTrinhCode the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public QuyTrinhDetailDTO findDetails(String quyTrinhCode) {
        log.debug("Request to get QuyTrinh detail: {}", quyTrinhCode);
        Optional<QuyTrinh> quiTrinh = quyTrinhRepository.findByQuyTrinhCode(quyTrinhCode);
        return converToQuyTrinhDetailDto(quiTrinh);
    }

    private QuyTrinhDetailDTO converToQuyTrinhDetailDto(Optional<QuyTrinh> optionalQuyTrinh) {
        if ( optionalQuyTrinh == null ) {
            return null;
        }

        QuyTrinh quyTrinh = optionalQuyTrinh.get();
        QuyTrinhDetailDTO quyTrinhDetailDTO = new QuyTrinhDetailDTO();
        quyTrinhDetailDTO.setId( quyTrinh.getId() );
        quyTrinhDetailDTO.setQuyTrinhCode( quyTrinh.getQuyTrinhCode() );
        quyTrinhDetailDTO.setName( quyTrinh.getName() );
        quyTrinhDetailDTO.setLoaiQuyTrinhId(quyTrinh.getLoaiQuyTrinh().getId());
        quyTrinhDetailDTO.setMethodName(quyTrinh.getLoaiQuyTrinh().getMethodName());
        quyTrinhDetailDTO.setEntityName(quyTrinh.getLoaiQuyTrinh().getEntityName());

        List<TienTrinhDetailDTO> tienTrinhXuLys = new ArrayList<>();

        quyTrinh.getTienTrinhs().forEach(
            tienTrinh -> {
                TienTrinhDetailDTO tienTrinhDetailDTO = new TienTrinhDetailDTO();
                tienTrinhDetailDTO.setTienTrinhBatDau(convertToTienTrinhDto(tienTrinh));

                List<TienTrinhDTO> tienTrinhKetThuc = builTienTrinhsKetThuc(tienTrinh.getTienTrinhXuLies(), quyTrinh);
                tienTrinhDetailDTO.setTienTrinhKetThucs(tienTrinhKetThuc);

                tienTrinhXuLys.add(tienTrinhDetailDTO);
            }
        );

        quyTrinhDetailDTO.setTienTrinhXuLys(tienTrinhXuLys);
        return quyTrinhDetailDTO;
    }

    private TienTrinhDTO convertToTienTrinhDto(TienTrinh tienTrinh) {
        if ( tienTrinh == null ) {
            return null;
        }

        TienTrinhDTO tienTrinhDTO = new TienTrinhDTO();
        tienTrinhDTO.setCreatedBy( tienTrinh.getCreatedBy() );
        tienTrinhDTO.setCreatedDate( tienTrinh.getCreatedDate() );
        tienTrinhDTO.setLastModifiedBy( tienTrinh.getLastModifiedBy() );
        tienTrinhDTO.setLastModifiedDate( tienTrinh.getLastModifiedDate() );
        tienTrinhDTO.setId( tienTrinh.getId() );
        tienTrinhDTO.setTienTrinhCode( tienTrinh.getTienTrinhCode() );
        tienTrinhDTO.setName( tienTrinh.getName() );
        tienTrinhDTO.setScreenCode( tienTrinh.getScreenCode() );
        tienTrinhDTO.setStatus( tienTrinh.getStatus() );

        return tienTrinhDTO;
    }

    private List<TienTrinhDTO> builTienTrinhsKetThuc(Set<TienTrinhXuLy> tienTrinhXuLies, QuyTrinh quyTrinh) {
        List<TienTrinhDTO> tienTrinhsKetThuc = new ArrayList<>();
        tienTrinhXuLies.forEach(
            tienTrinhXuLy -> {
                TienTrinhDTO tientrinhKetThucDTO =
                    convertToTienTrinhDto(
                    quyTrinh.getTienTrinhs()
                    .stream()
                    .filter(
                        tienTrinh ->
                            tienTrinh.getTienTrinhCode().equals(tienTrinhXuLy.getKetThucCode()))
                    .findFirst().orElse(null));
                tienTrinhsKetThuc.add(tientrinhKetThucDTO);
            }
        );
        return tienTrinhsKetThuc;
    }
}
