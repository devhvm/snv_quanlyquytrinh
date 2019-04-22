package com.manager.quanlyquytrinh.web.rest;
import com.manager.quanlyquytrinh.domain.QuyTrinh;
import com.manager.quanlyquytrinh.repository.QuyTrinhRepository;
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
 * REST controller for managing QuyTrinh.
 */
@RestController
@RequestMapping("/api")
public class QuyTrinhResource {

    private final Logger log = LoggerFactory.getLogger(QuyTrinhResource.class);

    private static final String ENTITY_NAME = "quanlyquytrinhQuyTrinh";

    private final QuyTrinhRepository quyTrinhRepository;

    public QuyTrinhResource(QuyTrinhRepository quyTrinhRepository) {
        this.quyTrinhRepository = quyTrinhRepository;
    }

    /**
     * POST  /quy-trinhs : Create a new quyTrinh.
     *
     * @param quyTrinh the quyTrinh to create
     * @return the ResponseEntity with status 201 (Created) and with body the new quyTrinh, or with status 400 (Bad Request) if the quyTrinh has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/quy-trinhs")
    public ResponseEntity<QuyTrinh> createQuyTrinh(@Valid @RequestBody QuyTrinh quyTrinh) throws URISyntaxException {
        log.debug("REST request to save QuyTrinh : {}", quyTrinh);
        if (quyTrinh.getId() != null) {
            throw new BadRequestAlertException("A new quyTrinh cannot already have an ID", ENTITY_NAME, "idexists");
        }
        QuyTrinh result = quyTrinhRepository.save(quyTrinh);
        return ResponseEntity.created(new URI("/api/quy-trinhs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /quy-trinhs : Updates an existing quyTrinh.
     *
     * @param quyTrinh the quyTrinh to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated quyTrinh,
     * or with status 400 (Bad Request) if the quyTrinh is not valid,
     * or with status 500 (Internal Server Error) if the quyTrinh couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/quy-trinhs")
    public ResponseEntity<QuyTrinh> updateQuyTrinh(@Valid @RequestBody QuyTrinh quyTrinh) throws URISyntaxException {
        log.debug("REST request to update QuyTrinh : {}", quyTrinh);
        if (quyTrinh.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        QuyTrinh result = quyTrinhRepository.save(quyTrinh);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, quyTrinh.getId().toString()))
            .body(result);
    }

    /**
     * GET  /quy-trinhs : get all the quyTrinhs.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of quyTrinhs in body
     */
    @GetMapping("/quy-trinhs")
    public List<QuyTrinh> getAllQuyTrinhs() {
        log.debug("REST request to get all QuyTrinhs");
        return quyTrinhRepository.findAll();
    }

    /**
     * GET  /quy-trinhs/:id : get the "id" quyTrinh.
     *
     * @param id the id of the quyTrinh to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the quyTrinh, or with status 404 (Not Found)
     */
    @GetMapping("/quy-trinhs/{id}")
    public ResponseEntity<QuyTrinh> getQuyTrinh(@PathVariable Long id) {
        log.debug("REST request to get QuyTrinh : {}", id);
        Optional<QuyTrinh> quyTrinh = quyTrinhRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(quyTrinh);
    }

    /**
     * DELETE  /quy-trinhs/:id : delete the "id" quyTrinh.
     *
     * @param id the id of the quyTrinh to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/quy-trinhs/{id}")
    public ResponseEntity<Void> deleteQuyTrinh(@PathVariable Long id) {
        log.debug("REST request to delete QuyTrinh : {}", id);
        quyTrinhRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
