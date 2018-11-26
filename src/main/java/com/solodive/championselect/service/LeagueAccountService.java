package com.solodive.championselect.service;

import com.solodive.championselect.domain.LeagueAccount;
import com.solodive.championselect.repository.LeagueAccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing LeagueAccount.
 */
@Service
@Transactional
public class LeagueAccountService {

    private final Logger log = LoggerFactory.getLogger(LeagueAccountService.class);

    private final LeagueAccountRepository leagueAccountRepository;

    public LeagueAccountService(LeagueAccountRepository leagueAccountRepository) {
        this.leagueAccountRepository = leagueAccountRepository;
    }

    /**
     * Save a leagueAccount.
     *
     * @param leagueAccount the entity to save
     * @return the persisted entity
     */
    public LeagueAccount save(LeagueAccount leagueAccount) {
        log.debug("Request to save LeagueAccount : {}", leagueAccount);        return leagueAccountRepository.save(leagueAccount);
    }

    /**
     * Get all the leagueAccounts.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<LeagueAccount> findAll(Pageable pageable) {
        log.debug("Request to get all LeagueAccounts");
        return leagueAccountRepository.findAll(pageable);
    }


    /**
     * Get one leagueAccount by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<LeagueAccount> findOne(Long id) {
        log.debug("Request to get LeagueAccount : {}", id);
        return leagueAccountRepository.findById(id);
    }

    /**
     * Delete the leagueAccount by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete LeagueAccount : {}", id);
        leagueAccountRepository.deleteById(id);
    }
}
