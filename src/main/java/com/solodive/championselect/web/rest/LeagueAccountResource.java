package com.solodive.championselect.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.solodive.championselect.domain.LeagueAccount;
import com.solodive.championselect.service.LeagueAccountService;
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
 * REST controller for managing LeagueAccount.
 */
@RestController
@RequestMapping("/api")
public class LeagueAccountResource {

    private final Logger log = LoggerFactory.getLogger(LeagueAccountResource.class);

    private static final String ENTITY_NAME = "leagueAccount";

    private final LeagueAccountService leagueAccountService;

    public LeagueAccountResource(LeagueAccountService leagueAccountService) {
        this.leagueAccountService = leagueAccountService;
    }

    /**
     * POST  /league-accounts : Create a new leagueAccount.
     *
     * @param leagueAccount the leagueAccount to create
     * @return the ResponseEntity with status 201 (Created) and with body the new leagueAccount, or with status 400 (Bad Request) if the leagueAccount has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/league-accounts")
    @Timed
    public ResponseEntity<LeagueAccount> createLeagueAccount(@RequestBody LeagueAccount leagueAccount) throws URISyntaxException {
        log.debug("REST request to save LeagueAccount : {}", leagueAccount);
        if (leagueAccount.getId() != null) {
            throw new BadRequestAlertException("A new leagueAccount cannot already have an ID", ENTITY_NAME, "idexists");
        }
        LeagueAccount result = leagueAccountService.save(leagueAccount);
        return ResponseEntity.created(new URI("/api/league-accounts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /league-accounts : Updates an existing leagueAccount.
     *
     * @param leagueAccount the leagueAccount to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated leagueAccount,
     * or with status 400 (Bad Request) if the leagueAccount is not valid,
     * or with status 500 (Internal Server Error) if the leagueAccount couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/league-accounts")
    @Timed
    public ResponseEntity<LeagueAccount> updateLeagueAccount(@RequestBody LeagueAccount leagueAccount) throws URISyntaxException {
        log.debug("REST request to update LeagueAccount : {}", leagueAccount);
        if (leagueAccount.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        LeagueAccount result = leagueAccountService.save(leagueAccount);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, leagueAccount.getId().toString()))
            .body(result);
    }

    /**
     * GET  /league-accounts : get all the leagueAccounts.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of leagueAccounts in body
     */
    @GetMapping("/league-accounts")
    @Timed
    public ResponseEntity<List<LeagueAccount>> getAllLeagueAccounts(Pageable pageable) {
        log.debug("REST request to get a page of LeagueAccounts");
        Page<LeagueAccount> page = leagueAccountService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/league-accounts");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /league-accounts/:id : get the "id" leagueAccount.
     *
     * @param id the id of the leagueAccount to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the leagueAccount, or with status 404 (Not Found)
     */
    @GetMapping("/league-accounts/{id}")
    @Timed
    public ResponseEntity<LeagueAccount> getLeagueAccount(@PathVariable Long id) {
        log.debug("REST request to get LeagueAccount : {}", id);
        Optional<LeagueAccount> leagueAccount = leagueAccountService.findOne(id);
        return ResponseUtil.wrapOrNotFound(leagueAccount);
    }

    /**
     * DELETE  /league-accounts/:id : delete the "id" leagueAccount.
     *
     * @param id the id of the leagueAccount to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/league-accounts/{id}")
    @Timed
    public ResponseEntity<Void> deleteLeagueAccount(@PathVariable Long id) {
        log.debug("REST request to delete LeagueAccount : {}", id);
        leagueAccountService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
