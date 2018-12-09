package com.solodive.championselect.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.solodive.championselect.domain.Champion;
import com.solodive.championselect.service.ChampionService;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
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
     * @return the ResponseEntity with status 200 (OK) and the list of champions in body, or with status 500 (Internal Server Error)
     */
    @GetMapping("/champions")
    @Timed
    public ResponseEntity<List<Champion>> getAllChampions(@RequestParam(required = false, defaultValue = "false") boolean eagerload) {

        log.info("Request to get all Champions");
        List<Champion> championList;

        try {

            championList = championService.findAll();
            log.info("Successfully returned all Champions : {}", championList);

        } catch (Exception exception) {

            log.error("Unable to get all Champions - error : {}", exception);

            return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .build();

        }

        return ResponseEntity.ok(championList);
    }

    /**
     * GET  /champions/:id : get the "id" champion.
     *
     * @param id the id of the champion to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the champion, or with status 404 (Not Found),
     *         or with status 500 (Internal Server Error)
     */
    @GetMapping("/champions/{id}")
    @Timed
    public ResponseEntity<Champion> getChampion(@PathVariable Long id) {

        log.info("Request to get Champion : {}", id);
        Optional<Champion> champion;

        try {

            champion = championService.findOne(id);
            log.info("Successfully returned Champion : {}", champion);

        } catch (Exception exception) {

            log.error("Unable to get Champion : {} - error : {}", id, exception);

            return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .build();

        }

        return ResponseUtil.wrapOrNotFound(champion);
    }
}
