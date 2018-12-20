package com.solodive.championselect.service;

import com.solodive.championselect.domain.Champion;
import com.solodive.championselect.repository.ChampionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Champion.
 */
@Service
@Transactional
public class ChampionService {

    private final Logger log = LoggerFactory.getLogger(ChampionService.class);

    private final ChampionRepository championRepository;

    public ChampionService(ChampionRepository championRepository) {
        this.championRepository = championRepository;
    }

    /**
     * Save a champion.
     *
     * @param champion the entity to save
     * @return the persisted entity
     */
    public Champion save(Champion champion) {
        log.debug("Request to save Champion : {}", champion);
        return championRepository.save(champion);
    }

    /**
     * Get all the champions.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<Champion> findAll(Pageable pageable) {
        log.debug("Request to get all Champions");
        return championRepository.findAll(pageable);
    }


    /**
     * Get one champion by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<Champion> findOne(Long id) {
        log.debug("Request to get Champion : {}", id);
        return championRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public Optional<Champion> findOneByRiotKey(Long id) {
        log.debug("Request to get Champion : {}", id);
        return championRepository.findFirstByRiotKey(id);
    }

    /**
     * Delete the champion by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Champion : {}", id);
        championRepository.deleteById(id);
    }
}
