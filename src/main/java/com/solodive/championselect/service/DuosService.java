package com.solodive.championselect.service;

import com.solodive.championselect.domain.Duos;
import com.solodive.championselect.repository.DuosRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing Duos.
 */
@Service
@Transactional
public class DuosService {

    private final Logger log = LoggerFactory.getLogger(DuosService.class);

    private final DuosRepository duosRepository;

    public DuosService(DuosRepository duosRepository) {
        this.duosRepository = duosRepository;
    }

    /**
     * Save a duos.
     *
     * @param duos the entity to save
     * @return the persisted entity
     */
    public Duos save(Duos duos) {
        log.debug("Request to save Duos : {}", duos);        return duosRepository.save(duos);
    }

    /**
     * Get all the duos.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<Duos> findAll() {
        log.debug("Request to get all Duos");
        return duosRepository.findAll();
    }


    /**
     * Get one duos by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<Duos> findOne(Long id) {
        log.debug("Request to get Duos : {}", id);
        return duosRepository.findById(id);
    }

    /**
     * Delete the duos by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Duos : {}", id);
        duosRepository.deleteById(id);
    }
}
