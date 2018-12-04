package com.solodive.championselect.repository;

import com.solodive.championselect.domain.ChampionTag;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ChampionTag entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ChampionTagRepository extends JpaRepository<ChampionTag, Long> {

}
