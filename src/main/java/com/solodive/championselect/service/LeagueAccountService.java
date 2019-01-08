package com.solodive.championselect.service;

import com.solodive.championselect.domain.*;
import com.solodive.championselect.repository.DuosRepository;
import com.solodive.championselect.repository.LeagueAccountRepository;
import com.solodive.championselect.repository.MostPlayedRepository;
import com.solodive.championselect.service.dto.riotapi.RiotExtendedSummonerDTO;
import com.solodive.championselect.service.dto.riotapi.RiotMatchDetailsDTO;
import com.solodive.championselect.service.dto.riotapi.RiotParticipantDTO;
import com.solodive.championselect.service.dto.riotapi.RiotParticipantIdentityDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing LeagueAccount.
 */
@Service
@Transactional
public class LeagueAccountService {

    @Autowired
    private SoloMemberService soloMemberService;

    @Autowired
    private ChampionService championService;

    @Autowired
    private DuosService duosService;

    @Autowired
    private MostPlayedService mostPlayedService;

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

    @Transactional(readOnly = true)
    public Optional<LeagueAccount> findOneBySummonersId(Long id) {
        log.debug("Request to get LeagueAccount : {}", id);
        return leagueAccountRepository.findOneBySummonersId(id);
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

    public LeagueAccount update(LeagueAccount leagueAccount) {
        log.debug("Request to update old leagueAccount record");
        LeagueAccount old = leagueAccountRepository.getOne(leagueAccount.getId());
        old.setLatest(false);
        old.setLastUpdate(Instant.now());
        save(old);
        leagueAccount.setLatest(true);
        leagueAccount.setLastUpdate(Instant.now());
        return save(leagueAccount);
    }

    public LeagueAccount mapRiotDTOToLeagueAccount(RiotExtendedSummonerDTO extendedSummoner) {
        return new LeagueAccount(extendedSummoner);
    }

    public HashMap<Long, Integer> mapMostPlayedWith(Long summonerId, List<RiotMatchDetailsDTO> riotMatchDetailsDTOS) {
        Set<Integer> mostPlayedWith = new HashSet<>();

        //get the occurences of duo
        HashMap<Long, Integer> matchMap = new HashMap<>();
        for (RiotMatchDetailsDTO match : riotMatchDetailsDTOS) {
            for (RiotParticipantIdentityDTO riotParticipantIdentityDTO : match.getParticipantIdentities()) {
                if (!riotParticipantIdentityDTO.getRiotPlayerDTO().getSummonerId().equals(summonerId))
                    if (matchMap.containsKey(riotParticipantIdentityDTO.getRiotPlayerDTO().getSummonerId())) {
                        matchMap.put(riotParticipantIdentityDTO.getRiotPlayerDTO().getSummonerId(),
                            matchMap.get(riotParticipantIdentityDTO.getRiotPlayerDTO().getSummonerId()) + 1);
                    } else {
                        matchMap.put(riotParticipantIdentityDTO.getRiotPlayerDTO().getSummonerId(), 1);
                    }
            }
        }

        //sort map and limit to 3
        HashMap<Long, Integer> sorted =
            matchMap.entrySet().stream()
                .sorted(Map.Entry.<Long, Integer>comparingByValue().reversed())
                .limit(3)
                .collect(Collectors.toMap(
                    Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        //get the solo members from db?? just insert into intersection? w/e
        return sorted;
    }

    public HashMap<Long, Integer> mapMostPlayed(Long summonerId, List<RiotMatchDetailsDTO> riotMatchDetailsDTOS) {
        Set<Champion> mostPlayed = new HashSet<>();
        HashMap<Long, Integer> matchMap = new HashMap<>();

        for (RiotMatchDetailsDTO match : riotMatchDetailsDTOS) {
            for (RiotParticipantDTO riotParticipantDTO : match.getRiotParticipantDTOS()) {
                if (match.getParticipantIdentities().get(riotParticipantDTO.getParticipantId().intValue() - 1).getRiotPlayerDTO().getSummonerId().equals(summonerId))
                    if (matchMap.containsKey(riotParticipantDTO.getChampionId())) {
                        matchMap.put(riotParticipantDTO.getChampionId(), matchMap.get(riotParticipantDTO.getChampionId()) + 1);
                    } else {
                        matchMap.put(riotParticipantDTO.getChampionId(), 1);
                    }
            }
        }

        //sort map and limit to 3
        HashMap<Long, Integer> sorted =
            matchMap.entrySet().stream()
                .sorted(Map.Entry.<Long, Integer>comparingByValue().reversed())
                .limit(3)
                .collect(Collectors.toMap(
                    Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        //get the solo members from db?? just insert into intersection? w/e
        return sorted;
    }

    public LeagueAccount mapAndSave(RiotExtendedSummonerDTO extendedSummoner) {
        log.debug("Request to save account : {}", extendedSummoner.getRiotBasicSummonerDTO());
        Optional<LeagueAccount> leagueAccount = findOneBySummonersId(extendedSummoner.getRiotBasicSummonerDTO().getId());
        if (leagueAccount.isPresent()) {
            log.debug("Is present");
        } else {
            LeagueAccount newAccount = mapRiotDTOToLeagueAccount(extendedSummoner);
            leagueAccountRepository.save(newAccount);
        }
        return null;
    }

    public void mapAndSave(Long summonerId, List<RiotMatchDetailsDTO> riotMatchDetailsDTOS) {
        Optional<SoloMember> soloMemberOptional = soloMemberService.findOneByAccount(summonerId);
        if (soloMemberOptional.isPresent()) {
            SoloMember soloMember = soloMemberOptional.get();
            log.debug("Found member {}", soloMember);
            mostPlayedService.removeAllByAccount(summonerId);
            duosService.removeAllByAccount(summonerId);

            for (Map.Entry<Long, Integer> entry : mapMostPlayedWith(summonerId, riotMatchDetailsDTOS).entrySet()) {
                //if it is not a solo member - omit this entry
                if (findOneBySummonersId(entry.getKey()).isPresent()) {
                    if (soloMemberService.findOneByAccount(entry.getKey()).isPresent()) {
                        Duos mostPlayedWith = new Duos();
                        mostPlayedWith.setMember(soloMember);
                        mostPlayedWith.setDuo(soloMemberService.findOneByAccount(entry.getKey()).get());
                        mostPlayedWith.setTimesPlayed(Long.valueOf(entry.getValue()));

                        duosService.save(mostPlayedWith);
                    }
                }
            }

            for (Map.Entry<Long, Integer> entry : mapMostPlayed(summonerId, riotMatchDetailsDTOS).entrySet()) {
                Optional<Champion> optionalChampion = championService.findOneByRiotKey(entry.getKey());
                //should always exsist in DB but just to make sure
                if (optionalChampion.isPresent()) {
                    MostPlayed mostPlayedAs = new MostPlayed();
                    mostPlayedAs.setMember(soloMember);
                    mostPlayedAs.setChampion(optionalChampion.get());
                    mostPlayedAs.setTimesPlayed(Long.valueOf(entry.getValue()));

                    mostPlayedService.save(mostPlayedAs);
                }
            }
        }
    }

}
