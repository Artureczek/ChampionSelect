package com.solodive.championselect.repository;

import com.solodive.championselect.domain.Duos;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Duos entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DuosRepository extends JpaRepository<Duos, Long> {

}
