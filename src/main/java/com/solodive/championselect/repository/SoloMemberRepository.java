package com.solodive.championselect.repository;

import com.solodive.championselect.domain.SoloMember;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;


/**
 * Spring Data  repository for the SoloMember entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SoloMemberRepository extends JpaRepository<SoloMember, Long> {

}
