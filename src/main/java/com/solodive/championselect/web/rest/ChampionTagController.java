package com.solodive.championselect.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.solodive.championselect.domain.ChampionTag;
import com.solodive.championselect.service.ChampionTagService;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing ChampionTag.
 */
@RestController
@RequestMapping("/api")
public class ChampionTagController {

    private final Logger log = LoggerFactory.getLogger(ChampionTagController.class);

    private static final String ENTITY_NAME = "championTag";

    private final ChampionTagService championTagService;

    public ChampionTagController(ChampionTagService championTagService) {
        this.championTagService = championTagService;
    }

    /**
     * GET  /champion-tags : get all the championTags.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of championTags in body, or with status 500 (Internal Server Error)
     */
    @GetMapping("/champion-tags")
    @Timed
    public ResponseEntity<List<ChampionTag>> getAllChampionTags() {

        log.info("Request to get all ChampionTags");
        List<ChampionTag> championTagList;

        try {

            championTagList = championTagService.findAll();
            log.info("Successfully returned all ChampionTags : {}", championTagList);

        } catch (Exception exception) {

            log.error("Unable to get all ChampionTags - error : {}", exception);

            return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .build();

        }

        return ResponseEntity.ok(championTagList);
    }

    /**
     * GET  /champion-tags/:id : get the "id" championTag.
     *
     * @param id the id of the championTag to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the championTag, or with status 404 (Not Found),
     *         or with status 500 (Internal Server Error)
     */
    @GetMapping("/champion-tags/{id}")
    @Timed
    public ResponseEntity<ChampionTag> getChampionTag(@PathVariable Long id) {

        log.info("REST request to get ChampionTag : {}", id);
        Optional<ChampionTag> championTag;

        try {

            championTag = championTagService.findOne(id);
            log.info("Successfully returned ChampionTag : {}", championTag);

        } catch (Exception exception) {

            log.error("Unable to get ChampionTag : {} - error : {}", id, exception);

            return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .build();

        }

        return ResponseUtil.wrapOrNotFound(championTag);
    }
}
