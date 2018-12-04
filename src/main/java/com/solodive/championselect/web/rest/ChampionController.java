package com.solodive.championselect.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.solodive.championselect.domain.Champion;
import com.solodive.championselect.service.ChampionService;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Champion.
 */
@RestController
@RequestMapping("/api")
public class ChampionController {

    private final Logger log = LoggerFactory.getLogger(ChampionController.class);

    private static final String ENTITY_NAME = "champion";

    private final ChampionService championService;

    public ChampionController(ChampionService championService) {
        this.championService = championService;
    }

    /**
     * GET  /champions : get all the champions.
     *
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many)
     * @return the ResponseEntity with status 200 (OK) and the list of champions in body
     */
    @GetMapping("/champions")
    @Timed
    public List<Champion> getAllChampions(@RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get all Champions");
        return championService.findAll();
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
}
