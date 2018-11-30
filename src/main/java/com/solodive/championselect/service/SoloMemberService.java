package com.solodive.championselect.service;

import com.solodive.championselect.domain.LeagueAccount;
import com.solodive.championselect.domain.SoloMember;
import com.solodive.championselect.repository.SoloMemberRepository;
import com.solodive.championselect.service.dto.riotapi.ExtendedSummoner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing SoloMember.
 */
@Service
@Transactional
public class SoloMemberService {

    private final Logger log = LoggerFactory.getLogger(SoloMemberService.class);

    private final SoloMemberRepository soloMemberRepository;

    public SoloMemberService(SoloMemberRepository soloMemberRepository) {
        this.soloMemberRepository = soloMemberRepository;
    }

    /**
     * Save a soloMember.
     *
     * @param soloMember the entity to save
     * @return the persisted entity
     */
    public SoloMember save(SoloMember soloMember) {
        log.debug("Request to save SoloMember : {}", soloMember);        return soloMemberRepository.save(soloMember);
    }

    /**
     * Get all the soloMembers.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<SoloMember> findAll(Pageable pageable) {
        log.debug("Request to get all SoloMembers");
        return soloMemberRepository.findAll(pageable);
    }

    /**
     * Get all the SoloMember with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<SoloMember> findAllWithEagerRelationships(Pageable pageable) {
        return soloMemberRepository.findAllWithEagerRelationships(pageable);
    }


    /**
     * Get one soloMember by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<SoloMember> findOne(Long id) {
        log.debug("Request to get SoloMember : {}", id);
        return soloMemberRepository.findOneWithEagerRelationships(id);
    }

    /**
     * Delete the soloMember by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete SoloMember : {}", id);
        soloMemberRepository.deleteById(id);
    }
}
