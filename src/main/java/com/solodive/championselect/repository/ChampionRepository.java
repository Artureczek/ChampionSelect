package com.solodive.championselect.repository;

import com.solodive.championselect.domain.Champion;
import org.springframework.data.jpa.repository.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import java.util.Optional;


/**
 * Spring Data  repository for the Champion entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ChampionRepository extends JpaRepository<Champion, Long> {
    Optional<Champion> findFirstByRiotKey(Long riotKey);
}
