package com.manager.quanlyquytrinh.web.rest;
import com.manager.quanlyquytrinh.service.TienTrinhService;
import com.manager.quanlyquytrinh.web.rest.errors.BadRequestAlertException;
import com.manager.quanlyquytrinh.web.rest.util.HeaderUtil;
import com.manager.quanlyquytrinh.web.rest.util.PaginationUtil;
import com.manager.quanlyquytrinh.service.dto.TienTrinhDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing TienTrinh.
 */
@RestController
@RequestMapping("/api")
public class TienTrinhResource {

    private final Logger log = LoggerFactory.getLogger(TienTrinhResource.class);

    private static final String ENTITY_NAME = "quanlyquytrinhTienTrinh";

    private final TienTrinhService tienTrinhService;

    public TienTrinhResource(TienTrinhService tienTrinhService) {
        this.tienTrinhService = tienTrinhService;
    }

    /**
     * POST  /tien-trinhs : Create a new tienTrinh.
     *
     * @param tienTrinhDTO the tienTrinhDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new tienTrinhDTO, or with status 400 (Bad Request) if the tienTrinh has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/tien-trinhs")
    public ResponseEntity<TienTrinhDTO> createTienTrinh(@Valid @RequestBody TienTrinhDTO tienTrinhDTO) throws URISyntaxException {
        log.debug("REST request to save TienTrinh : {}", tienTrinhDTO);
        if (tienTrinhDTO.getId() != null) {
            throw new BadRequestAlertException("A new tienTrinh cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TienTrinhDTO result = tienTrinhService.save(tienTrinhDTO);
        return ResponseEntity.created(new URI("/api/tien-trinhs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /tien-trinhs : Updates an existing tienTrinh.
     *
     * @param tienTrinhDTO the tienTrinhDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated tienTrinhDTO,
     * or with status 400 (Bad Request) if the tienTrinhDTO is not valid,
     * or with status 500 (Internal Server Error) if the tienTrinhDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/tien-trinhs")
    public ResponseEntity<TienTrinhDTO> updateTienTrinh(@Valid @RequestBody TienTrinhDTO tienTrinhDTO) throws URISyntaxException {
        log.debug("REST request to update TienTrinh : {}", tienTrinhDTO);
        if (tienTrinhDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TienTrinhDTO result = tienTrinhService.save(tienTrinhDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, tienTrinhDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /tien-trinhs : get all the tienTrinhs.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of tienTrinhs in body
     */
    @GetMapping("/tien-trinhs")
    public ResponseEntity<List<TienTrinhDTO>> getAllTienTrinhs(Pageable pageable) {
        log.debug("REST request to get a page of TienTrinhs");
        Page<TienTrinhDTO> page = tienTrinhService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/tien-trinhs");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /tien-trinhs/:id : get the "id" tienTrinh.
     *
     * @param id the id of the tienTrinhDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the tienTrinhDTO, or with status 404 (Not Found)
     */
    @GetMapping("/tien-trinhs/{id}")
    public ResponseEntity<TienTrinhDTO> getTienTrinh(@PathVariable Long id) {
        log.debug("REST request to get TienTrinh : {}", id);
        Optional<TienTrinhDTO> tienTrinhDTO = tienTrinhService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tienTrinhDTO);
    }

    /**
     * DELETE  /tien-trinhs/:id : delete the "id" tienTrinh.
     *
     * @param id the id of the tienTrinhDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/tien-trinhs/{id}")
    public ResponseEntity<Void> deleteTienTrinh(@PathVariable Long id) {
        log.debug("REST request to delete TienTrinh : {}", id);
        tienTrinhService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
