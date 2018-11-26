package com.solodive.championselect.repository;

import com.solodive.championselect.domain.SoloMember;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the SoloMember entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SoloMemberRepository extends JpaRepository<SoloMember, Long> {

    @Query(value = "select distinct solo_member from SoloMember solo_member left join fetch solo_member.mostPlayeds left join fetch solo_member.members",
        countQuery = "select count(distinct solo_member) from SoloMember solo_member")
    Page<SoloMember> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct solo_member from SoloMember solo_member left join fetch solo_member.mostPlayeds left join fetch solo_member.members")
    List<SoloMember> findAllWithEagerRelationships();

    @Query("select solo_member from SoloMember solo_member left join fetch solo_member.mostPlayeds left join fetch solo_member.members where solo_member.id =:id")
    Optional<SoloMember> findOneWithEagerRelationships(@Param("id") Long id);

}
