package com.solodive.championselect.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.solodive.championselect.domain.MostPlayed;
import com.solodive.championselect.service.MostPlayedService;
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
 * REST controller for managing MostPlayed.
 */
@RestController
@RequestMapping("/api")
public class MostPlayedResource {

    private final Logger log = LoggerFactory.getLogger(MostPlayedResource.class);

    private static final String ENTITY_NAME = "mostPlayed";

    private final MostPlayedService mostPlayedService;

    public MostPlayedResource(MostPlayedService mostPlayedService) {
        this.mostPlayedService = mostPlayedService;
    }

    /**
     * POST  /most-playeds : Create a new mostPlayed.
     *
     * @param mostPlayed the mostPlayed to create
     * @return the ResponseEntity with status 201 (Created) and with body the new mostPlayed, or with status 400 (Bad Request) if the mostPlayed has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/most-playeds")
    @Timed
    public ResponseEntity<MostPlayed> createMostPlayed(@RequestBody MostPlayed mostPlayed) throws URISyntaxException {
        log.debug("REST request to save MostPlayed : {}", mostPlayed);
        if (mostPlayed.getId() != null) {
            throw new BadRequestAlertException("A new mostPlayed cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MostPlayed result = mostPlayedService.save(mostPlayed);
        return ResponseEntity.created(new URI("/api/most-playeds/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /most-playeds : Updates an existing mostPlayed.
     *
     * @param mostPlayed the mostPlayed to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated mostPlayed,
     * or with status 400 (Bad Request) if the mostPlayed is not valid,
     * or with status 500 (Internal Server Error) if the mostPlayed couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/most-playeds")
    @Timed
    public ResponseEntity<MostPlayed> updateMostPlayed(@RequestBody MostPlayed mostPlayed) throws URISyntaxException {
        log.debug("REST request to update MostPlayed : {}", mostPlayed);
        if (mostPlayed.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MostPlayed result = mostPlayedService.save(mostPlayed);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, mostPlayed.getId().toString()))
            .body(result);
    }

    /**
     * GET  /most-playeds : get all the mostPlayeds.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of mostPlayeds in body
     */
    @GetMapping("/most-playeds")
    @Timed
    public List<MostPlayed> getAllMostPlayeds() {
        log.debug("REST request to get all MostPlayeds");
        return mostPlayedService.findAll();
    }

    /**
     * GET  /most-playeds/:id : get the "id" mostPlayed.
     *
     * @param id the id of the mostPlayed to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the mostPlayed, or with status 404 (Not Found)
     */
    @GetMapping("/most-playeds/{id}")
    @Timed
    public ResponseEntity<MostPlayed> getMostPlayed(@PathVariable Long id) {
        log.debug("REST request to get MostPlayed : {}", id);
        Optional<MostPlayed> mostPlayed = mostPlayedService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mostPlayed);
    }

    /**
     * DELETE  /most-playeds/:id : delete the "id" mostPlayed.
     *
     * @param id the id of the mostPlayed to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/most-playeds/{id}")
    @Timed
    public ResponseEntity<Void> deleteMostPlayed(@PathVariable Long id) {
        log.debug("REST request to delete MostPlayed : {}", id);
        mostPlayedService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
