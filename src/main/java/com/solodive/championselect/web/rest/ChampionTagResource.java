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
public class ChampionTagResource {

    private final Logger log = LoggerFactory.getLogger(ChampionTagResource.class);

    private static final String ENTITY_NAME = "championTag";

    private final ChampionTagService championTagService;

    public ChampionTagResource(ChampionTagService championTagService) {
        this.championTagService = championTagService;
    }

    /**
     * POST  /champion-tags : Create a new championTag.
     *
     * @param championTag the championTag to create
     * @return the ResponseEntity with status 201 (Created) and with body the new championTag, or with status 400 (Bad Request) if the championTag has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/champion-tags")
    @Timed
    public ResponseEntity<ChampionTag> createChampionTag(@Valid @RequestBody ChampionTag championTag) throws URISyntaxException {
        log.debug("REST request to save ChampionTag : {}", championTag);
        if (championTag.getId() != null) {
            throw new BadRequestAlertException("A new championTag cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ChampionTag result = championTagService.save(championTag);
        return ResponseEntity.created(new URI("/api/champion-tags/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /champion-tags : Updates an existing championTag.
     *
     * @param championTag the championTag to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated championTag,
     * or with status 400 (Bad Request) if the championTag is not valid,
     * or with status 500 (Internal Server Error) if the championTag couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/champion-tags")
    @Timed
    public ResponseEntity<ChampionTag> updateChampionTag(@Valid @RequestBody ChampionTag championTag) throws URISyntaxException {
        log.debug("REST request to update ChampionTag : {}", championTag);
        if (championTag.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ChampionTag result = championTagService.save(championTag);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, championTag.getId().toString()))
            .body(result);
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

    /**
     * DELETE  /champion-tags/:id : delete the "id" championTag.
     *
     * @param id the id of the championTag to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/champion-tags/{id}")
    @Timed
    public ResponseEntity<Void> deleteChampionTag(@PathVariable Long id) {
        log.debug("REST request to delete ChampionTag : {}", id);
        championTagService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
