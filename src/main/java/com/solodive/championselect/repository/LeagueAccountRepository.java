package com.solodive.championselect.repository;

import com.solodive.championselect.domain.LeagueAccount;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.Optional;


/**
 * Spring Data  repository for the LeagueAccount entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LeagueAccountRepository extends JpaRepository<LeagueAccount, Long> {
        Optional<LeagueAccount> findOneBySummonersId(Long summonersId);
}
