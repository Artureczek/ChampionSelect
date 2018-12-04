package com.solodive.championselect.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.solodive.championselect.domain.ChampionTag;
import com.solodive.championselect.service.ChampionTagService;
import com.solodive.championselect.web.rest.errors.BadRequestAlertException;
import com.solodive.championselect.web.rest.util.HeaderUtil;
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
     * @return the ResponseEntity with status 200 (OK) and the list of championTags in body
     */
    @GetMapping("/champion-tags")
    @Timed
    public List<ChampionTag> getAllChampionTags() {
        log.debug("REST request to get all ChampionTags");
        return championTagService.findAll();
    }

    /**
     * GET  /champion-tags/:id : get the "id" championTag.
     *
     * @param id the id of the championTag to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the championTag, or with status 404 (Not Found)
     */
    @GetMapping("/champion-tags/{id}")
    @Timed
    public ResponseEntity<ChampionTag> getChampionTag(@PathVariable Long id) {
        log.debug("REST request to get ChampionTag : {}", id);
        Optional<ChampionTag> championTag = championTagService.findOne(id);
        return ResponseUtil.wrapOrNotFound(championTag);
    }
}
