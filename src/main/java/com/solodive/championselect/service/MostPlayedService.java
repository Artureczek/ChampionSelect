package com.solodive.championselect.service;

import com.solodive.championselect.domain.MostPlayed;
import com.solodive.championselect.repository.MostPlayedRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing MostPlayed.
 */
@Service
@Transactional
public class MostPlayedService {

    private final Logger log = LoggerFactory.getLogger(MostPlayedService.class);

    private final MostPlayedRepository mostPlayedRepository;

    public MostPlayedService(MostPlayedRepository mostPlayedRepository) {
        this.mostPlayedRepository = mostPlayedRepository;
    }

    /**
     * Save a mostPlayed.
     *
     * @param mostPlayed the entity to save
     * @return the persisted entity
     */
    public MostPlayed save(MostPlayed mostPlayed) {
        log.debug("Request to save MostPlayed : {}", mostPlayed);        return mostPlayedRepository.save(mostPlayed);
    }

    /**
     * Get all the mostPlayeds.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<MostPlayed> findAll() {
        log.debug("Request to get all MostPlayeds");
        return mostPlayedRepository.findAll();
    }


    /**
     * Get one mostPlayed by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<MostPlayed> findOne(Long id) {
        log.debug("Request to get MostPlayed : {}", id);
        return mostPlayedRepository.findById(id);
    }

    /**
     * Delete the mostPlayed by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete MostPlayed : {}", id);
        mostPlayedRepository.deleteById(id);
    }
}
