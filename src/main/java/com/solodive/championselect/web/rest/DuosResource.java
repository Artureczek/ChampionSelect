package com.solodive.championselect.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.solodive.championselect.domain.Duos;
import com.solodive.championselect.service.DuosService;
import com.solodive.championselect.web.rest.errors.BadRequestAlertException;
import com.solodive.championselect.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Duos.
 */
@RestController
@RequestMapping("/api")
public class DuosResource {

    private final Logger log = LoggerFactory.getLogger(DuosResource.class);

    private static final String ENTITY_NAME = "duos";

    private final DuosService duosService;

    public DuosResource(DuosService duosService) {
        this.duosService = duosService;
    }

    /**
     * POST  /duos : Create a new duos.
     *
     * @param duos the duos to create
     * @return the ResponseEntity with status 201 (Created) and with body the new duos, or with status 400 (Bad Request) if the duos has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/duos")
    @Timed
    public ResponseEntity<Duos> createDuos(@RequestBody Duos duos) throws URISyntaxException {
        log.debug("REST request to save Duos : {}", duos);
        if (duos.getId() != null) {
            throw new BadRequestAlertException("A new duos cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Duos result = duosService.save(duos);
        return ResponseEntity.created(new URI("/api/duos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /duos : Updates an existing duos.
     *
     * @param duos the duos to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated duos,
     * or with status 400 (Bad Request) if the duos is not valid,
     * or with status 500 (Internal Server Error) if the duos couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/duos")
    @Timed
    public ResponseEntity<Duos> updateDuos(@RequestBody Duos duos) throws URISyntaxException {
        log.debug("REST request to update Duos : {}", duos);
        if (duos.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Duos result = duosService.save(duos);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, duos.getId().toString()))
            .body(result);
    }

    /**
     * GET  /duos : get all the duos.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of duos in body
     */
    @GetMapping("/duos")
    @Timed
    public List<Duos> getAllDuos() {
        log.debug("REST request to get all Duos");
        return duosService.findAll();
    }

    /**
     * GET  /duos/:id : get the "id" duos.
     *
     * @param id the id of the duos to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the duos, or with status 404 (Not Found)
     */
    @GetMapping("/duos/{id}")
    @Timed
    public ResponseEntity<Duos> getDuos(@PathVariable Long id) {
        log.debug("REST request to get Duos : {}", id);
        Optional<Duos> duos = duosService.findOne(id);
        return ResponseUtil.wrapOrNotFound(duos);
    }

    /**
     * DELETE  /duos/:id : delete the "id" duos.
     *
     * @param id the id of the duos to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/duos/{id}")
    @Timed
    public ResponseEntity<Void> deleteDuos(@PathVariable Long id) {
        log.debug("REST request to delete Duos : {}", id);
        duosService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
