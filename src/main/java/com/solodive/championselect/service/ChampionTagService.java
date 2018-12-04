package com.solodive.championselect.service;

import com.solodive.championselect.domain.ChampionTag;
import com.solodive.championselect.repository.ChampionTagRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing ChampionTag.
 */
@Service
@Transactional
public class ChampionTagService {

    private final Logger log = LoggerFactory.getLogger(ChampionTagService.class);

    private final ChampionTagRepository championTagRepository;

    public ChampionTagService(ChampionTagRepository championTagRepository) {
        this.championTagRepository = championTagRepository;
    }

    /**
     * Save a championTag.
     *
     * @param championTag the entity to save
     * @return the persisted entity
     */
    public ChampionTag save(ChampionTag championTag) {
        log.debug("Request to save ChampionTag : {}", championTag);        return championTagRepository.save(championTag);
    }

    /**
     * Get all the championTags.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<ChampionTag> findAll() {
        log.debug("Request to get all ChampionTags");
        return championTagRepository.findAll();
    }


    /**
     * Get one championTag by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<ChampionTag> findOne(Long id) {
        log.debug("Request to get ChampionTag : {}", id);
        return championTagRepository.findById(id);
    }

    /**
     * Delete the championTag by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete ChampionTag : {}", id);
        championTagRepository.deleteById(id);
    }
}
