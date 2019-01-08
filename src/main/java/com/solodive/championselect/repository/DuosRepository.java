package com.solodive.championselect.repository;

import com.solodive.championselect.domain.Duos;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


/**
 * Spring Data  repository for the Duos entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DuosRepository extends JpaRepository<Duos, Long> {
    @Query("select d from Duos d inner join SoloMember sm on sm.id = d.member inner join LeagueAccount la on la.id = sm.account where la.summonersId = :account")
    Optional<List<Duos>> findAllByAccount(@Param("account") Long account);
}
