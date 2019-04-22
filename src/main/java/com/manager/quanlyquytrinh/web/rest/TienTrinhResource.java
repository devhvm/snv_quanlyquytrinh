package com.manager.quanlyquytrinh.web.rest;
import com.manager.quanlyquytrinh.domain.TienTrinh;
import com.manager.quanlyquytrinh.repository.TienTrinhRepository;
import com.manager.quanlyquytrinh.web.rest.errors.BadRequestAlertException;
import com.manager.quanlyquytrinh.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final TienTrinhRepository tienTrinhRepository;

    public TienTrinhResource(TienTrinhRepository tienTrinhRepository) {
        this.tienTrinhRepository = tienTrinhRepository;
    }

    /**
     * POST  /tien-trinhs : Create a new tienTrinh.
     *
     * @param tienTrinh the tienTrinh to create
     * @return the ResponseEntity with status 201 (Created) and with body the new tienTrinh, or with status 400 (Bad Request) if the tienTrinh has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/tien-trinhs")
    public ResponseEntity<TienTrinh> createTienTrinh(@Valid @RequestBody TienTrinh tienTrinh) throws URISyntaxException {
        log.debug("REST request to save TienTrinh : {}", tienTrinh);
        if (tienTrinh.getId() != null) {
            throw new BadRequestAlertException("A new tienTrinh cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TienTrinh result = tienTrinhRepository.save(tienTrinh);
        return ResponseEntity.created(new URI("/api/tien-trinhs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /tien-trinhs : Updates an existing tienTrinh.
     *
     * @param tienTrinh the tienTrinh to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated tienTrinh,
     * or with status 400 (Bad Request) if the tienTrinh is not valid,
     * or with status 500 (Internal Server Error) if the tienTrinh couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/tien-trinhs")
    public ResponseEntity<TienTrinh> updateTienTrinh(@Valid @RequestBody TienTrinh tienTrinh) throws URISyntaxException {
        log.debug("REST request to update TienTrinh : {}", tienTrinh);
        if (tienTrinh.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TienTrinh result = tienTrinhRepository.save(tienTrinh);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, tienTrinh.getId().toString()))
            .body(result);
    }

    /**
     * GET  /tien-trinhs : get all the tienTrinhs.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of tienTrinhs in body
     */
    @GetMapping("/tien-trinhs")
    public List<TienTrinh> getAllTienTrinhs() {
        log.debug("REST request to get all TienTrinhs");
        return tienTrinhRepository.findAll();
    }

    /**
     * GET  /tien-trinhs/:id : get the "id" tienTrinh.
     *
     * @param id the id of the tienTrinh to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the tienTrinh, or with status 404 (Not Found)
     */
    @GetMapping("/tien-trinhs/{id}")
    public ResponseEntity<TienTrinh> getTienTrinh(@PathVariable Long id) {
        log.debug("REST request to get TienTrinh : {}", id);
        Optional<TienTrinh> tienTrinh = tienTrinhRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(tienTrinh);
    }

    /**
     * DELETE  /tien-trinhs/:id : delete the "id" tienTrinh.
     *
     * @param id the id of the tienTrinh to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/tien-trinhs/{id}")
    public ResponseEntity<Void> deleteTienTrinh(@PathVariable Long id) {
        log.debug("REST request to delete TienTrinh : {}", id);
        tienTrinhRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
