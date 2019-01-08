package com.solodive.championselect.service;

import com.solodive.championselect.domain.Champion;
import com.solodive.championselect.domain.enumeration.ChampionTagValue;
import com.solodive.championselect.repository.ChampionRepository;
import com.solodive.championselect.service.dto.riotapi.RiotChampionDTO;
import com.solodive.championselect.service.exception.ChampionTagUnknownValueException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    private final ChampionTagService championTagService;

    public ChampionService(ChampionRepository championRepository,
                           ChampionTagService championTagService) {
        this.championRepository = championRepository;
        this.championTagService = championTagService;
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

    /**
     * Map and save a riotChampionDTO as Champion.
     *
     * @param riotChampionDTO the entity to map and save as Champion
     * @return the persisted Champion entity
     */
    public Champion save(RiotChampionDTO riotChampionDTO) {
        log.debug("Request to map and save RiotChampionDTO as Champion : {}", riotChampionDTO);
        if (riotChampionDTO != null) {
            return championRepository.save(
                attachTags(
                    new Champion(riotChampionDTO),
                    riotChampionDTO.getTags()
                )
            );
        }
        return null;
    }

    /**
     * Map and save a list of riotChampionDTOs as list of Champions.
     *
     * @param riotChampionDTOList list of entities to map and save as list of Champions
     * @return the persisted list of Champion entities
     */
    public List<Champion> save(List<RiotChampionDTO> riotChampionDTOList) {
        log.debug("Request to map and save list of RiotChampionDTOs as Champions : {}", riotChampionDTOList);
        if (riotChampionDTOList != null && riotChampionDTOList.size() > 0) {
            return championRepository.saveAll(
                riotChampionDTOList
                    .stream()
                    .map(riotChampionDTO ->
                        attachTags(
                            new Champion(riotChampionDTO),
                            riotChampionDTO.getTags()
                        ))
                    .collect(Collectors.toList())
            );
        }
        return new ArrayList<>();
    }

    private Champion attachTags(Champion champion, List<String> stringTags) {
        if (stringTags != null && stringTags.size() > 0) {
            champion
                .addMultipleTags(
                    championTagService.findByTagIn(
                        stringTags
                            .stream()
                            .map(ChampionTagValue::getType)
                            .collect(Collectors.toList())
                    )
                );
        }
        return champion;
    }
}
