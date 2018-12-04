package com.solodive.championselect.service;

import com.solodive.championselect.domain.Champion;
import com.solodive.championselect.repository.ChampionRepository;
import com.solodive.championselect.service.dto.riotapi.RiotChampionDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<Champion> findAll() {
        log.debug("Request to get all Champions");
        return championRepository.findAllWithEagerRelationships();
    }

    /**
     * Get all the Champion with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<Champion> findAllWithEagerRelationships(Pageable pageable) {
        return championRepository.findAllWithEagerRelationships(pageable);
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
        return championRepository.findOneWithEagerRelationships(id);
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

    public Champion save(RiotChampionDTO riotChampionDTO) {
        return championRepository.save(new Champion(riotChampionDTO));
    }

    public List<Champion> save(List<RiotChampionDTO> riotChampionDTOList) {
        return championRepository.saveAll(
            riotChampionDTOList
                .stream()
                .map(Champion::new)
                .collect(Collectors.toList())
        );
    }
}
