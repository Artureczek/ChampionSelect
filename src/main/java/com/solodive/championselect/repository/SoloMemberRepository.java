package com.solodive.championselect.repository;

import com.solodive.championselect.domain.LeagueAccount;
import com.solodive.championselect.domain.SoloMember;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;


/**
 * Spring Data  repository for the SoloMember entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SoloMemberRepository extends JpaRepository<SoloMember, Long> {
    @Query("select sm from SoloMember sm inner join LeagueAccount la on la.id = sm.account where la.summonersId = :account")
    Optional<SoloMember> findOneByAccount(@Param("account") Long account);
}
