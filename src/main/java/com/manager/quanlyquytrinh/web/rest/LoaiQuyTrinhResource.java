package com.manager.quanlyquytrinh.web.rest;
import com.manager.quanlyquytrinh.service.LoaiQuyTrinhService;
import com.manager.quanlyquytrinh.web.rest.errors.BadRequestAlertException;
import com.manager.quanlyquytrinh.web.rest.util.HeaderUtil;
import com.manager.quanlyquytrinh.web.rest.util.PaginationUtil;
import com.manager.quanlyquytrinh.service.dto.LoaiQuyTrinhDTO;
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
 * REST controller for managing LoaiQuyTrinh.
 */
@RestController
@RequestMapping("/api")
public class LoaiQuyTrinhResource {

    private final Logger log = LoggerFactory.getLogger(LoaiQuyTrinhResource.class);

    private static final String ENTITY_NAME = "quanlyquytrinhLoaiQuyTrinh";

    private final LoaiQuyTrinhService loaiQuyTrinhService;

    public LoaiQuyTrinhResource(LoaiQuyTrinhService loaiQuyTrinhService) {
        this.loaiQuyTrinhService = loaiQuyTrinhService;
    }

    /**
     * POST  /loai-quy-trinhs : Create a new loaiQuyTrinh.
     *
     * @param loaiQuyTrinhDTO the loaiQuyTrinhDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new loaiQuyTrinhDTO, or with status 400 (Bad Request) if the loaiQuyTrinh has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/loai-quy-trinhs")
    public ResponseEntity<LoaiQuyTrinhDTO> createLoaiQuyTrinh(@Valid @RequestBody LoaiQuyTrinhDTO loaiQuyTrinhDTO) throws URISyntaxException {
        log.debug("REST request to save LoaiQuyTrinh : {}", loaiQuyTrinhDTO);
        if (loaiQuyTrinhDTO.getId() != null) {
            throw new BadRequestAlertException("A new loaiQuyTrinh cannot already have an ID", ENTITY_NAME, "idexists");
        }
        LoaiQuyTrinhDTO result = loaiQuyTrinhService.save(loaiQuyTrinhDTO);
        return ResponseEntity.created(new URI("/api/loai-quy-trinhs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /loai-quy-trinhs : Updates an existing loaiQuyTrinh.
     *
     * @param loaiQuyTrinhDTO the loaiQuyTrinhDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated loaiQuyTrinhDTO,
     * or with status 400 (Bad Request) if the loaiQuyTrinhDTO is not valid,
     * or with status 500 (Internal Server Error) if the loaiQuyTrinhDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/loai-quy-trinhs")
    public ResponseEntity<LoaiQuyTrinhDTO> updateLoaiQuyTrinh(@Valid @RequestBody LoaiQuyTrinhDTO loaiQuyTrinhDTO) throws URISyntaxException {
        log.debug("REST request to update LoaiQuyTrinh : {}", loaiQuyTrinhDTO);
        if (loaiQuyTrinhDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        LoaiQuyTrinhDTO result = loaiQuyTrinhService.save(loaiQuyTrinhDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, loaiQuyTrinhDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /loai-quy-trinhs : get all the loaiQuyTrinhs.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of loaiQuyTrinhs in body
     */
    @GetMapping("/loai-quy-trinhs")
    public ResponseEntity<List<LoaiQuyTrinhDTO>> getAllLoaiQuyTrinhs(Pageable pageable) {
        log.debug("REST request to get a page of LoaiQuyTrinhs");
        Page<LoaiQuyTrinhDTO> page = loaiQuyTrinhService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/loai-quy-trinhs");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /loai-quy-trinhs/:id : get the "id" loaiQuyTrinh.
     *
     * @param id the id of the loaiQuyTrinhDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the loaiQuyTrinhDTO, or with status 404 (Not Found)
     */
    @GetMapping("/loai-quy-trinhs/{id}")
    public ResponseEntity<LoaiQuyTrinhDTO> getLoaiQuyTrinh(@PathVariable Long id) {
        log.debug("REST request to get LoaiQuyTrinh : {}", id);
        Optional<LoaiQuyTrinhDTO> loaiQuyTrinhDTO = loaiQuyTrinhService.findOne(id);
        return ResponseUtil.wrapOrNotFound(loaiQuyTrinhDTO);
    }

    /**
     * DELETE  /loai-quy-trinhs/:id : delete the "id" loaiQuyTrinh.
     *
     * @param id the id of the loaiQuyTrinhDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/loai-quy-trinhs/{id}")
    public ResponseEntity<Void> deleteLoaiQuyTrinh(@PathVariable Long id) {
        log.debug("REST request to delete LoaiQuyTrinh : {}", id);
        loaiQuyTrinhService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
