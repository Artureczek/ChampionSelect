package com.solodive.championselect.repository;

import com.solodive.championselect.domain.ChampionTag;
import com.solodive.championselect.domain.enumeration.ChampionTagValue;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


/**
 * Spring Data  repository for the ChampionTag entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ChampionTagRepository extends JpaRepository<ChampionTag, Long> {

    Optional<ChampionTag> findByTag(ChampionTagValue championTagValue);

    List<ChampionTag> findByTagIn(List<ChampionTagValue> championTagValueList);
}
