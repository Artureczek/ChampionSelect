package com.solodive.championselect.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.solodive.championselect.domain.SoloMember;
import com.solodive.championselect.service.SoloMemberService;
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
 * REST controller for managing SoloMember.
 */
@RestController
@RequestMapping("/api")
public class SoloMemberResource {

    private final Logger log = LoggerFactory.getLogger(SoloMemberResource.class);

    private static final String ENTITY_NAME = "soloMember";

    private final SoloMemberService soloMemberService;

    public SoloMemberResource(SoloMemberService soloMemberService) {
        this.soloMemberService = soloMemberService;
    }

    /**
     * POST  /solo-members : Create a new soloMember.
     *
     * @param soloMember the soloMember to create
     * @return the ResponseEntity with status 201 (Created) and with body the new soloMember, or with status 400 (Bad Request) if the soloMember has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/solo-members")
    @Timed
    public ResponseEntity<SoloMember> createSoloMember(@RequestBody SoloMember soloMember) throws URISyntaxException {
        log.debug("REST request to save SoloMember : {}", soloMember);
        if (soloMember.getId() != null) {
            throw new BadRequestAlertException("A new soloMember cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SoloMember result = soloMemberService.save(soloMember);
        return ResponseEntity.created(new URI("/api/solo-members/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /solo-members : Updates an existing soloMember.
     *
     * @param soloMember the soloMember to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated soloMember,
     * or with status 400 (Bad Request) if the soloMember is not valid,
     * or with status 500 (Internal Server Error) if the soloMember couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/solo-members")
    @Timed
    public ResponseEntity<SoloMember> updateSoloMember(@RequestBody SoloMember soloMember) throws URISyntaxException {
        log.debug("REST request to update SoloMember : {}", soloMember);
        if (soloMember.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SoloMember result = soloMemberService.save(soloMember);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, soloMember.getId().toString()))
            .body(result);
    }

    /**
     * GET  /solo-members : get all the soloMembers.
     *
     * @param pageable the pagination information
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many)
     * @return the ResponseEntity with status 200 (OK) and the list of soloMembers in body
     */
    @GetMapping("/solo-members")
    @Timed
    public ResponseEntity<List<SoloMember>> getAllSoloMembers(Pageable pageable, @RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get a page of SoloMembers");
        Page<SoloMember> page;
        if (eagerload) {
            page = soloMemberService.findAllWithEagerRelationships(pageable);
        } else {
            page = soloMemberService.findAll(pageable);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, String.format("/api/solo-members?eagerload=%b", eagerload));
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /solo-members/:id : get the "id" soloMember.
     *
     * @param id the id of the soloMember to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the soloMember, or with status 404 (Not Found)
     */
    @GetMapping("/solo-members/{id}")
    @Timed
    public ResponseEntity<SoloMember> getSoloMember(@PathVariable Long id) {
        log.debug("REST request to get SoloMember : {}", id);
        Optional<SoloMember> soloMember = soloMemberService.findOne(id);
        return ResponseUtil.wrapOrNotFound(soloMember);
    }

    /**
     * DELETE  /solo-members/:id : delete the "id" soloMember.
     *
     * @param id the id of the soloMember to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/solo-members/{id}")
    @Timed
    public ResponseEntity<Void> deleteSoloMember(@PathVariable Long id) {
        log.debug("REST request to delete SoloMember : {}", id);
        soloMemberService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
