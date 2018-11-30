package com.solodive.championselect.service;

import com.solodive.championselect.domain.Champion;
import com.solodive.championselect.domain.LeagueAccount;
import com.solodive.championselect.domain.SoloMember;
import com.solodive.championselect.repository.LeagueAccountRepository;
import com.solodive.championselect.service.dto.riotapi.ExtendedSummoner;
import com.solodive.championselect.service.dto.riotapi.MatchDetails;
import com.solodive.championselect.service.dto.riotapi.Participant;
import com.solodive.championselect.service.dto.riotapi.ParticipantIdentity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Map.Entry.comparingByValue;

/**
 * Service Implementation for managing LeagueAccount.
 */
@Service
@Transactional
public class LeagueAccountService {

    private final Logger log = LoggerFactory.getLogger(LeagueAccountService.class);

    private final LeagueAccountRepository leagueAccountRepository;

    public LeagueAccountService(LeagueAccountRepository leagueAccountRepository) {
        this.leagueAccountRepository = leagueAccountRepository;
    }

    /**
     * Save a leagueAccount.
     *
     * @param leagueAccount the entity to save
     * @return the persisted entity
     */
    public LeagueAccount save(LeagueAccount leagueAccount) {
        log.debug("Request to save LeagueAccount : {}", leagueAccount);
        return leagueAccountRepository.save(leagueAccount);
    }

    /**
     * Get all the leagueAccounts.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<LeagueAccount> findAll(Pageable pageable) {
        log.debug("Request to get all LeagueAccounts");
        return leagueAccountRepository.findAll(pageable);
    }


    /**
     * Get one leagueAccount by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<LeagueAccount> findOne(Long id) {
        log.debug("Request to get LeagueAccount : {}", id);
        return leagueAccountRepository.findById(id);
    }

    /**
     * Delete the leagueAccount by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete LeagueAccount : {}", id);
        leagueAccountRepository.deleteById(id);
    }

    public LeagueAccount mapRiotDTOToLeagueAccount(ExtendedSummoner extendedSummoner) {
        return new LeagueAccount(extendedSummoner);
    }

    public List<Long> mapMostPlayedWith(Long summonerId, List<MatchDetails> matchDetailsList) {
        Set<Integer> mostPlayedWith = new HashSet<>();

        //get the occurences of duo
        HashMap<Long, Integer> matchMap = new HashMap<>();
        for (MatchDetails match : matchDetailsList) {
            for (ParticipantIdentity participantIdentity : match.getParticipantIdentities()) {
                if (participantIdentity.getPlayer().getAccountId().equals(summonerId))
                    if (matchMap.containsKey(participantIdentity.getPlayer().getAccountId())) {
                        matchMap.put(participantIdentity.getPlayer().getAccountId(), matchMap.get(participantIdentity.getPlayer().getAccountId()) + 1);
                    } else {
                        matchMap.put(participantIdentity.getPlayer().getAccountId(), 1);
                    }
            }
        }

        //sort map and limit to 3
        HashMap<Long, Integer> sorted =
            matchMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .limit(3)
                .collect(Collectors.toMap(
                    Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        ArrayList<Long> sortedList = new ArrayList<>();
        //get top 3
        Iterator it = sorted.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            System.out.println(pair.getKey() + " = " + pair.getValue());
            sortedList.add((Long)pair.getKey());
            it.remove(); // avoids a ConcurrentModificationException
        }


        //get the solo members from db?? just insert into intersection? w/e
        return sortedList;
    }

    public List<Long> mapMostPlayed(Long summonerId, List<MatchDetails> matchDetailsList) {
        Set<Champion> mostPlayed = new HashSet<>();

        HashMap<Long, Integer> matchMap = new HashMap<>();
        for (MatchDetails match : matchDetailsList) {
            for (Participant participant : match.getParticipants()) {
                if (participant.getParticipantId().equals(summonerId))
                    if (matchMap.containsKey(participant.getChampionId())) {
                        matchMap.put(participant.getChampionId(), matchMap.get(participant.getParticipantId() + 1));
                    } else {
                        matchMap.put(participant.getParticipantId(), 1);
                    }
            }
        }

        //sort map and limit to 3
        HashMap<Long, Integer> sorted =
            matchMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .limit(3)
                .collect(Collectors.toMap(
                    Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        ArrayList<Long> sortedList = new ArrayList<>();
        //get top 3
        Iterator it = sorted.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            sortedList.add((Long)pair.getKey());
            it.remove(); // avoids a ConcurrentModificationException
        }

        //get the solo members from db?? just insert into intersection? w/e
        return sortedList;
    }

    public LeagueAccount mapAndSave(ExtendedSummoner extendedSummoner) {
        LeagueAccount leagueAccount = mapRiotDTOToLeagueAccount(extendedSummoner);
        log.debug("Request to save LeagueAccount : {}", leagueAccount);
        return leagueAccountRepository.save(leagueAccount);
    }
}
