package com.solodive.championselect.repository;

import com.solodive.championselect.domain.Champion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Champion entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ChampionRepository extends JpaRepository<Champion, Long> {

    @Query(value = "select distinct champion from Champion champion left join fetch champion.tags",
        countQuery = "select count(distinct champion) from Champion champion")
    Page<Champion> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct champion from Champion champion left join fetch champion.tags")
    List<Champion> findAllWithEagerRelationships();

    @Query("select champion from Champion champion left join fetch champion.tags where champion.id =:id")
    Optional<Champion> findOneWithEagerRelationships(@Param("id") Long id);

    Optional<Champion> findFirstByRiotKey(Long riotKey);

}
