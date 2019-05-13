package com.manager.quanlyquytrinh.web.rest;
import com.manager.quanlyquytrinh.service.QuyTrinhService;
import com.manager.quanlyquytrinh.service.dto.QuyTrinhDetailDTO;
import com.manager.quanlyquytrinh.web.rest.errors.BadRequestAlertException;
import com.manager.quanlyquytrinh.web.rest.util.HeaderUtil;
import com.manager.quanlyquytrinh.web.rest.util.PaginationUtil;
import com.manager.quanlyquytrinh.service.dto.QuyTrinhDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing QuyTrinh.
 */
@RestController
@RequestMapping("/api")
public class QuyTrinhResource {

    private final Logger log = LoggerFactory.getLogger(QuyTrinhResource.class);

    private static final String ENTITY_NAME = "quanlyquytrinhQuyTrinh";

    private final QuyTrinhService quyTrinhService;

    public QuyTrinhResource(QuyTrinhService quyTrinhService) {
        this.quyTrinhService = quyTrinhService;
    }

    /**
     * POST  /quy-trinhs : Create a new quyTrinh.
     *
     * @param quyTrinhDTO the quyTrinhDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new quyTrinhDTO, or with status 400 (Bad Request) if the quyTrinh has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/quy-trinhs")
    public ResponseEntity<QuyTrinhDTO> createQuyTrinh(@Valid @RequestBody QuyTrinhDTO quyTrinhDTO) throws URISyntaxException {
        log.debug("REST request to save QuyTrinh : {}", quyTrinhDTO);
        if (quyTrinhDTO.getId() != null) {
            throw new BadRequestAlertException("A new quyTrinh cannot already have an ID", ENTITY_NAME, "idexists");
        }
        QuyTrinhDTO result = quyTrinhService.save(quyTrinhDTO);
        return ResponseEntity.created(new URI("/api/quy-trinhs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /quy-trinhs : Updates an existing quyTrinh.
     *
     * @param quyTrinhDTO the quyTrinhDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated quyTrinhDTO,
     * or with status 400 (Bad Request) if the quyTrinhDTO is not valid,
     * or with status 500 (Internal Server Error) if the quyTrinhDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/quy-trinhs")
    public ResponseEntity<QuyTrinhDTO> updateQuyTrinh(@Valid @RequestBody QuyTrinhDTO quyTrinhDTO) throws URISyntaxException {
        log.debug("REST request to update QuyTrinh : {}", quyTrinhDTO);
        if (quyTrinhDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        QuyTrinhDTO result = quyTrinhService.save(quyTrinhDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, quyTrinhDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /quy-trinhs : get all the quyTrinhs.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of quyTrinhs in body
     */
    @GetMapping("/quy-trinhs")
    public ResponseEntity<List<QuyTrinhDTO>> getAllQuyTrinhs(Pageable pageable) {
        log.debug("REST request to get a page of QuyTrinhs");
        Page<QuyTrinhDTO> page = quyTrinhService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/quy-trinhs");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /quy-trinhs/:id : get the "id" quyTrinh.
     *
     * @param id the id of the quyTrinhDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the quyTrinhDTO, or with status 404 (Not Found)
     */
    @GetMapping("/quy-trinhs/{id}")
    public ResponseEntity<QuyTrinhDTO> getQuyTrinh(@PathVariable Long id) {
        log.debug("REST request to get QuyTrinh : {}", id);
        Optional<QuyTrinhDTO> quyTrinhDTO = quyTrinhService.findOne(id);
        return ResponseUtil.wrapOrNotFound(quyTrinhDTO);
    }

    /**
     * DELETE  /quy-trinhs/:id : delete the "id" quyTrinh.
     *
     * @param id the id of the quyTrinhDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/quy-trinhs/{id}")
    public ResponseEntity<Void> deleteQuyTrinh(@PathVariable Long id) {
        log.debug("REST request to delete QuyTrinh : {}", id);
        quyTrinhService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * GET  /quy-trinhs-detail/:id : get the "id" quyTrinh.
     *
     * @param id the id of the quyTrinhDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the quyTrinhDTO, or with status 404 (Not Found)
     */
    @GetMapping("/quy-trinhs-detail/{quyTrinhCode}")
    public ResponseEntity<QuyTrinhDetailDTO> getQuyTrinhsDetail(@PathVariable String quyTrinhCode) {
        log.debug("REST request to get QuyTrinh detail: {}", quyTrinhCode);
        QuyTrinhDetailDTO quyTrinhDetailDTO = quyTrinhService.findDetails(quyTrinhCode);
        return ResponseEntity.ok().body(quyTrinhDetailDTO);
    }
}
