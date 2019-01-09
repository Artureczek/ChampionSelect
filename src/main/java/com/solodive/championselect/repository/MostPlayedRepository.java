package com.solodive.championselect.repository;

import com.solodive.championselect.domain.MostPlayed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


/**
 * Spring Data  repository for the MostPlayed entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MostPlayedRepository extends JpaRepository<MostPlayed, Long> {
    @Query("select mp from MostPlayed mp inner join SoloMember sm on sm.id = mp.member inner join LeagueAccount la on la.id = sm.account where la.summonersId = :account")
    Optional<List<MostPlayed>> findAllByAccount(@Param("account") Long account);
}
