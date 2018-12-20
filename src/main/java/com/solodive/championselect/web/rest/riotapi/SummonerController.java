package com.solodive.championselect.web.rest.riotapi;

import com.codahale.metrics.annotation.Timed;
import com.solodive.championselect.domain.LeagueAccount;
import com.solodive.championselect.service.LeagueAccountService;
import com.solodive.championselect.service.SoloMemberService;
import com.solodive.championselect.service.dto.riotapi.*;
import com.solodive.championselect.service.riotapi.RiotSummonerService;
import com.solodive.championselect.web.rest.UserResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/riotapi")
public class SummonerController {

    private final Logger log = LoggerFactory.getLogger(UserResource.class);
    @Autowired
    private RiotSummonerService riotSummonerService;
    private RiotBasicSummonerDTO riotBasicSummonerDTO;
    private RiotExtendedSummonerDTO riotExtendedSummonerDTO;
    private List<RiotSummonerRankDTO> riotSummonerRankDTO;
    private RiotSummonerMatchesDTO riotSummonerMatchesDTO;
    private RiotMatchDetailsDTO riotMatchDetailsDTO;

    @Autowired
    private LeagueAccountService leagueAccountService;

    @Autowired
    private SoloMemberService soloMemberService;

    @GetMapping("/summoner/by-name/{name}") //consumes= MediaType.APPLICATION_JSON_UTF8_VALUE,value=
    @Timed
    public ResponseEntity<RiotBasicSummonerDTO> getSummonerByName(@PathVariable String name) {
        riotBasicSummonerDTO = riotSummonerService.getBasicSummonerByName(name);
        return new ResponseEntity<>(riotBasicSummonerDTO, null, HttpStatus.OK);
    }

    @GetMapping("/summoner/by-id/{id}")
    @Timed
    public ResponseEntity<RiotBasicSummonerDTO> getSummonerById(@PathVariable String id) {
        riotBasicSummonerDTO = riotSummonerService.getBasicSummonerById(id);
        return new ResponseEntity<>(riotBasicSummonerDTO, null, HttpStatus.OK);
    }


    @GetMapping("/summoner-rank/{id}")
    @Timed
    public ResponseEntity<List<RiotSummonerRankDTO>> getSummonerRankById(@PathVariable String id) {
        riotSummonerRankDTO = riotSummonerService.getSummonerRankById(id);
        return new ResponseEntity<>(riotSummonerRankDTO, null, HttpStatus.OK);
    }

    @GetMapping("/summoner-matches/{id}")
    @Timed
    public ResponseEntity<RiotSummonerMatchesDTO> getSummonerMatchesById(@PathVariable String id) {
        riotSummonerMatchesDTO = riotSummonerService.getSummonerMatchList(id);
        return new ResponseEntity<>(riotSummonerMatchesDTO, null, HttpStatus.OK);
    }


    @GetMapping("/extended-summoner/{id}")
    @Timed
    public ResponseEntity<RiotExtendedSummonerDTO> getExtendedSummonerById(@PathVariable String id) {
        riotExtendedSummonerDTO = riotSummonerService.getExtendedSummonerById(id);
        LeagueAccount leagueAccount = leagueAccountService.mapAndSave(riotExtendedSummonerDTO);

        RiotMatchDetailsDTO riotMatchDetailsDTO = getMatchDetailsById("2083767571").getBody();
        RiotMatchDetailsDTO riotMatchDetailsDTO2 = getMatchDetailsById("2083749436").getBody();
        RiotMatchDetailsDTO riotMatchDetailsDTO3 = getMatchDetailsById("2082740427").getBody();
        RiotMatchDetailsDTO riotMatchDetailsDTO4 = getMatchDetailsById("2082712887").getBody();
        RiotMatchDetailsDTO riotMatchDetailsDTO5 = getMatchDetailsById("2082707928").getBody();

        List<RiotMatchDetailsDTO> riotMatchDetailsDTOList = new ArrayList<>();
        riotMatchDetailsDTOList.add(riotMatchDetailsDTO);
        riotMatchDetailsDTOList.add(riotMatchDetailsDTO2);
        riotMatchDetailsDTOList.add(riotMatchDetailsDTO3);
        riotMatchDetailsDTOList.add(riotMatchDetailsDTO4);
        riotMatchDetailsDTOList.add(riotMatchDetailsDTO5);

        System.out.println(riotMatchDetailsDTO);

        try {
            leagueAccountService.mapAndSave(36858200L, riotMatchDetailsDTOList);
            System.out.println("########################### Most played : " + leagueAccountService.mapMostPlayed(riotExtendedSummonerDTO.getRiotBasicSummonerDTO().getId(), riotMatchDetailsDTOList));
            System.out.println("########################### Most played with : " + leagueAccountService.mapMostPlayedWith(riotExtendedSummonerDTO.getRiotBasicSummonerDTO().getId(), riotMatchDetailsDTOList));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(riotExtendedSummonerDTO, null, HttpStatus.OK);
    }

    @GetMapping("/match/{id}")
    @Timed
    public ResponseEntity<RiotMatchDetailsDTO> getMatchDetailsById(@PathVariable String id) {
        riotMatchDetailsDTO = riotSummonerService.getMatchDetails(id);
        return new ResponseEntity<>(riotMatchDetailsDTO, null, HttpStatus.OK);
    }
}
