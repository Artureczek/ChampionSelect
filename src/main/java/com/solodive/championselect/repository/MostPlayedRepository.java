package com.solodive.championselect.repository;

import com.solodive.championselect.domain.MostPlayed;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MostPlayed entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MostPlayedRepository extends JpaRepository<MostPlayed, Long> {

}
