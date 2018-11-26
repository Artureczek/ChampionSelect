package com.solodive.championselect.repository;

import com.solodive.championselect.domain.Champion;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Champion entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ChampionRepository extends JpaRepository<Champion, Long> {

}
