package com.solodive.championselect.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.solodive.championselect.domain.Quote;
import com.solodive.championselect.service.QuoteService;
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
 * REST controller for managing Quote.
 */
@RestController
@RequestMapping("/api")
public class QuoteResource {

    private final Logger log = LoggerFactory.getLogger(QuoteResource.class);

    private static final String ENTITY_NAME = "quote";

    private final QuoteService quoteService;

    public QuoteResource(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    /**
     * POST  /quotes : Create a new quote.
     *
     * @param quote the quote to create
     * @return the ResponseEntity with status 201 (Created) and with body the new quote, or with status 400 (Bad Request) if the quote has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/quotes")
    @Timed
    public ResponseEntity<Quote> createQuote(@RequestBody Quote quote) throws URISyntaxException {
        log.debug("REST request to save Quote : {}", quote);
        if (quote.getId() != null) {
            throw new BadRequestAlertException("A new quote cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Quote result = quoteService.save(quote);
        return ResponseEntity.created(new URI("/api/quotes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /quotes : Updates an existing quote.
     *
     * @param quote the quote to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated quote,
     * or with status 400 (Bad Request) if the quote is not valid,
     * or with status 500 (Internal Server Error) if the quote couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/quotes")
    @Timed
    public ResponseEntity<Quote> updateQuote(@RequestBody Quote quote) throws URISyntaxException {
        log.debug("REST request to update Quote : {}", quote);
        if (quote.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Quote result = quoteService.save(quote);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, quote.getId().toString()))
            .body(result);
    }

    /**
     * GET  /quotes : get all the quotes.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of quotes in body
     */
    @GetMapping("/quotes")
    @Timed
    public ResponseEntity<List<Quote>> getAllQuotes(Pageable pageable) {
        log.debug("REST request to get a page of Quotes");
        Page<Quote> page = quoteService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/quotes");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /quotes/:id : get the "id" quote.
     *
     * @param id the id of the quote to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the quote, or with status 404 (Not Found)
     */
    @GetMapping("/quotes/{id}")
    @Timed
    public ResponseEntity<Quote> getQuote(@PathVariable Long id) {
        log.debug("REST request to get Quote : {}", id);
        Optional<Quote> quote = quoteService.findOne(id);
        return ResponseUtil.wrapOrNotFound(quote);
    }

    /**
     * DELETE  /quotes/:id : delete the "id" quote.
     *
     * @param id the id of the quote to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/quotes/{id}")
    @Timed
    public ResponseEntity<Void> deleteQuote(@PathVariable Long id) {
        log.debug("REST request to delete Quote : {}", id);
        quoteService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
