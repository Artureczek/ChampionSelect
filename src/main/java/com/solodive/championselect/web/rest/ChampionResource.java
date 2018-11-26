package com.solodive.championselect.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.solodive.championselect.domain.Champion;
import com.solodive.championselect.service.ChampionService;
import com.solodive.championselect.web.rest.errors.BadRequestAlertException;
import com.solodive.championselect.web.rest.util.HeaderUtil;
import com.solodive.championselect.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Champion.
 */
@RestController
@RequestMapping("/api")
public class ChampionResource {

    private final Logger log = LoggerFactory.getLogger(ChampionResource.class);

    private static final String ENTITY_NAME = "champion";

    private final ChampionService championService;

    public ChampionResource(ChampionService championService) {
        this.championService = championService;
    }

    /**
     * POST  /champions : Create a new champion.
     *
     * @param champion the champion to create
     * @return the ResponseEntity with status 201 (Created) and with body the new champion, or with status 400 (Bad Request) if the champion has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/champions")
    @Timed
    public ResponseEntity<Champion> createChampion(@RequestBody Champion champion) throws URISyntaxException {
        log.debug("REST request to save Champion : {}", champion);
        if (champion.getId() != null) {
            throw new BadRequestAlertException("A new champion cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Champion result = championService.save(champion);
        return ResponseEntity.created(new URI("/api/champions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /champions : Updates an existing champion.
     *
     * @param champion the champion to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated champion,
     * or with status 400 (Bad Request) if the champion is not valid,
     * or with status 500 (Internal Server Error) if the champion couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/champions")
    @Timed
    public ResponseEntity<Champion> updateChampion(@RequestBody Champion champion) throws URISyntaxException {
        log.debug("REST request to update Champion : {}", champion);
        if (champion.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Champion result = championService.save(champion);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, champion.getId().toString()))
            .body(result);
    }

    /**
     * GET  /champions : get all the champions.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of champions in body
     */
    @GetMapping("/champions")
    @Timed
    public ResponseEntity<List<Champion>> getAllChampions(Pageable pageable) {
        log.debug("REST request to get a page of Champions");
        Page<Champion> page = championService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/champions");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /champions/:id : get the "id" champion.
     *
     * @param id the id of the champion to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the champion, or with status 404 (Not Found)
     */
    @GetMapping("/champions/{id}")
    @Timed
    public ResponseEntity<Champion> getChampion(@PathVariable Long id) {
        log.debug("REST request to get Champion : {}", id);
        Optional<Champion> champion = championService.findOne(id);
        return ResponseUtil.wrapOrNotFound(champion);
    }

    /**
     * DELETE  /champions/:id : delete the "id" champion.
     *
     * @param id the id of the champion to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/champions/{id}")
    @Timed
    public ResponseEntity<Void> deleteChampion(@PathVariable Long id) {
        log.debug("REST request to delete Champion : {}", id);
        championService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
