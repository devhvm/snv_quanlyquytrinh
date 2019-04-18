package com.manager.quanlyquytrinh.web.rest;
import com.manager.quanlyquytrinh.service.TienTrinhXuLyService;
import com.manager.quanlyquytrinh.web.rest.errors.BadRequestAlertException;
import com.manager.quanlyquytrinh.web.rest.util.HeaderUtil;
import com.manager.quanlyquytrinh.web.rest.util.PaginationUtil;
import com.manager.quanlyquytrinh.service.dto.TienTrinhXuLyDTO;
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
 * REST controller for managing TienTrinhXuLy.
 */
@RestController
@RequestMapping("/api")
public class TienTrinhXuLyResource {

    private final Logger log = LoggerFactory.getLogger(TienTrinhXuLyResource.class);

    private static final String ENTITY_NAME = "quanlyquytrinhTienTrinhXuLy";

    private final TienTrinhXuLyService tienTrinhXuLyService;

    public TienTrinhXuLyResource(TienTrinhXuLyService tienTrinhXuLyService) {
        this.tienTrinhXuLyService = tienTrinhXuLyService;
    }

    /**
     * POST  /tien-trinh-xu-lies : Create a new tienTrinhXuLy.
     *
     * @param tienTrinhXuLyDTO the tienTrinhXuLyDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new tienTrinhXuLyDTO, or with status 400 (Bad Request) if the tienTrinhXuLy has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/tien-trinh-xu-lies")
    public ResponseEntity<TienTrinhXuLyDTO> createTienTrinhXuLy(@Valid @RequestBody TienTrinhXuLyDTO tienTrinhXuLyDTO) throws URISyntaxException {
        log.debug("REST request to save TienTrinhXuLy : {}", tienTrinhXuLyDTO);
        if (tienTrinhXuLyDTO.getId() != null) {
            throw new BadRequestAlertException("A new tienTrinhXuLy cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TienTrinhXuLyDTO result = tienTrinhXuLyService.save(tienTrinhXuLyDTO);
        return ResponseEntity.created(new URI("/api/tien-trinh-xu-lies/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /tien-trinh-xu-lies : Updates an existing tienTrinhXuLy.
     *
     * @param tienTrinhXuLyDTO the tienTrinhXuLyDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated tienTrinhXuLyDTO,
     * or with status 400 (Bad Request) if the tienTrinhXuLyDTO is not valid,
     * or with status 500 (Internal Server Error) if the tienTrinhXuLyDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/tien-trinh-xu-lies")
    public ResponseEntity<TienTrinhXuLyDTO> updateTienTrinhXuLy(@Valid @RequestBody TienTrinhXuLyDTO tienTrinhXuLyDTO) throws URISyntaxException {
        log.debug("REST request to update TienTrinhXuLy : {}", tienTrinhXuLyDTO);
        if (tienTrinhXuLyDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TienTrinhXuLyDTO result = tienTrinhXuLyService.save(tienTrinhXuLyDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, tienTrinhXuLyDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /tien-trinh-xu-lies : get all the tienTrinhXuLies.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of tienTrinhXuLies in body
     */
    @GetMapping("/tien-trinh-xu-lies")
    public ResponseEntity<List<TienTrinhXuLyDTO>> getAllTienTrinhXuLies(Pageable pageable) {
        log.debug("REST request to get a page of TienTrinhXuLies");
        Page<TienTrinhXuLyDTO> page = tienTrinhXuLyService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/tien-trinh-xu-lies");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /tien-trinh-xu-lies/:id : get the "id" tienTrinhXuLy.
     *
     * @param id the id of the tienTrinhXuLyDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the tienTrinhXuLyDTO, or with status 404 (Not Found)
     */
    @GetMapping("/tien-trinh-xu-lies/{id}")
    public ResponseEntity<TienTrinhXuLyDTO> getTienTrinhXuLy(@PathVariable Long id) {
        log.debug("REST request to get TienTrinhXuLy : {}", id);
        Optional<TienTrinhXuLyDTO> tienTrinhXuLyDTO = tienTrinhXuLyService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tienTrinhXuLyDTO);
    }

    /**
     * DELETE  /tien-trinh-xu-lies/:id : delete the "id" tienTrinhXuLy.
     *
     * @param id the id of the tienTrinhXuLyDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/tien-trinh-xu-lies/{id}")
    public ResponseEntity<Void> deleteTienTrinhXuLy(@PathVariable Long id) {
        log.debug("REST request to delete TienTrinhXuLy : {}", id);
        tienTrinhXuLyService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
